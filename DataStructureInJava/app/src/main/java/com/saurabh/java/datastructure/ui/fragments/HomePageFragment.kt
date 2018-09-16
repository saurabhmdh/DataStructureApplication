package com.saurabh.java.datastructure.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentHomeBinding
import com.saurabh.java.datastructure.ui.adapters.HomepageListAdapter
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.Category
import java.util.*


class HomePageFragment : Fragment() {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentHomeBinding>()
    var adapter by autoCleared<HomepageListAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false, dataBindingComponent)
        setHasOptionsMenu(true);
        dataBinding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDashboardStyleToList()
    }

    private fun setDashboardStyleToList() {
        dataBinding.recyclerviewGrid.layoutManager = GridLayoutManager(activity, 2)
        this@HomePageFragment.adapter = HomepageListAdapter(dataBindingComponent)
        dataBinding.recyclerviewGrid.adapter = this@HomePageFragment.adapter

        adapter.replace(getTitleDataList())
    }


    private fun getTitleDataList(): ArrayList<Category> {
        val _beans = ArrayList<Category>()

        _beans.add(Category(1, "Linked List", R.color.colorTitle1, R.drawable.ic_dashboard_linked_list_24dp))
        _beans.add(Category(2, "Stack", R.color.colorTitle2, R.drawable.ic_dashboard_stack_24dp))
        _beans.add(Category(3, "Queues", R.color.colorTitle3, R.drawable.ic_dashboard_queue_24dp))

        _beans.add(Category(4, "Trees", R.color.colorTitle4, R.drawable.ic_dashboard_tree_24dp))
        _beans.add(Category(5, "Graphs", R.color.colorTitle5, R.drawable.ic_dashboard_graph_24dp))
        _beans.add(Category(6, "Searching", R.color.colorTitle6, R.drawable.ic_dashboard_search_code_24dp))

        _beans.add(Category(7, "Sorting", R.color.colorTitle7, R.drawable.ic_dashboard_sort_24dp))
        _beans.add(Category(8, "Interview questions", R.color.colorTitle8, R.drawable.ic_dashboard_faq_24dp))

        return _beans
    }
}