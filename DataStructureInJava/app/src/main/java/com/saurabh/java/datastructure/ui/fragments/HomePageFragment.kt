package com.saurabh.java.datastructure.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import androidx.recyclerview.widget.GridLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentHomeBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.adapters.HomepageListAdapter
import com.saurabh.java.datastructure.util.LookupTable
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category

import java.util.*
import javax.inject.Inject


class HomePageFragment : BaseFragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentHomeBinding>()
    private var adapter by autoCleared<HomepageListAdapter>()

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var lookupTable: LookupTable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false, dataBindingComponent)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupGridView()
    }

    private fun setupGridView() {
        dataBinding.recyclerviewGrid.layoutManager = GridLayoutManager(activity, 2)
        this@HomePageFragment.adapter = HomepageListAdapter(dataBindingComponent,appExecutors,
                object : HomepageListAdapter.CategoryClickCallback{
                    override fun onClick(category: Category) {
                        onClickCategory(category)
                    }
                })

        dataBinding.recyclerviewGrid.adapter = this@HomePageFragment.adapter
        adapter.submitList(getTitleDataList())
    }

    private fun onClickCategory(category: Category) {
        if (category.titleId < 7) {
            gotoSection(category)
        } else {
            Navigation.findNavController(activity!!, R.id.nav_host_fragment).navigate(HomePageFragmentDirections.actionNavigateFaq())
        }
    }

    private fun gotoSection(category: Category) {
        Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                .navigate(HomePageFragmentDirections.actionNavigateProgram(category, category.titleId))
    }


    private fun getTitleDataList(): ArrayList<Category> {
        return lookupTable.getAllCategory()
    }

    override fun getTitle(): ActionbarItem {
        return ActionbarItem(getString(R.string.app_name), R.drawable.ic_home)
    }

}