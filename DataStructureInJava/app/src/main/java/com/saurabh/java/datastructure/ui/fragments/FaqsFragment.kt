package com.saurabh.java.datastructure.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentFaqsBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.viewmodel.FAQViewModel
import timber.log.Timber
import javax.inject.Inject

class FaqsFragment : Fragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentFaqsBinding>()
    var adapter by autoCleared<FragmentFaqsBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFaqsBinding>(inflater, R.layout.fragment_faqs, container, false, dataBindingComponent)
        dataBinding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUIComponents()
        setupListView()
        setupViewModel()
    }

    private fun setupViewModel() {
        val homeVM = ViewModelProviders.of(this, viewModelFactory).get(FAQViewModel::class.java)
        homeVM.getAllFaqs().observe(this, Observer {data ->
            data?.let {
                for(test in it) {
                    Timber.i(test.question)
                }
            }
        })
    }

    private fun setupUIComponents() {
        activity?.title = "Interview questions"
    }

    private fun setupListView() {
        //TODO : Write code
    }
}