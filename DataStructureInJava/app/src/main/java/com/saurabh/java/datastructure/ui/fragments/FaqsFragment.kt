package com.saurabh.java.datastructure.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentFaqsBinding
import com.saurabh.java.datastructure.db.tables.FAQ
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.adapters.FAQsListAdapter
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.viewmodel.FAQViewModel
import com.saurabh.java.datastructure.vo.ActionbarItem

import javax.inject.Inject

class FaqsFragment : BaseFragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentFaqsBinding>()

    private var adapter by autoCleared<FAQsListAdapter>()
    private lateinit var faqVM : FAQViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentFaqsBinding.inflate(inflater, container,false, dataBindingComponent)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListView()
        setupViewModel()
    }

    private fun setupViewModel() {
        faqVM = ViewModelProviders.of(this, viewModelFactory).get(FAQViewModel::class.java)

        faqVM.getAllFaqs().observe(this, Observer {data ->
            data?.let {
                adapter.submitList(it.toMutableList())
            }
        })
    }


    override fun getTitle(): ActionbarItem {
        return ActionbarItem(getString(R.string.faq), R.drawable.ic_dashboard_faq_24dp)
    }

    private fun setupListView() {
        val layoutManager = LinearLayoutManager(activity)
        dataBinding.recyclerviewFaqs.layoutManager = layoutManager
        dataBinding.recyclerviewFaqs.setHasFixedSize(true)

        this@FaqsFragment.adapter = FAQsListAdapter(dataBindingComponent, appExecutors,
                object : FAQsListAdapter.FAQsListClickCallback {
                    override fun onClick(faq: FAQ, position: Int) {
                        faq.isOpen = if (faq.isOpen == 0) 1 else 0
                        faqVM.updateFAQ(faq)
                    }
        })
        dataBinding.recyclerviewFaqs.adapter = this@FaqsFragment.adapter
    }
}