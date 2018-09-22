package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.databinding.FragmentHomeBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.interfaces.IFragmentLifeCycleEvent
import com.saurabh.java.datastructure.ui.adapters.HomepageListAdapter
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.util.instanceOf
import com.saurabh.java.datastructure.vo.Category
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class HomePageFragment : Fragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentHomeBinding>()
    var adapter by autoCleared<HomepageListAdapter>()
    lateinit var iFragmentLifeCycleEvent: IFragmentLifeCycleEvent

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IFragmentLifeCycleEvent) {
            iFragmentLifeCycleEvent = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false, dataBindingComponent)
        dataBinding = binding
        return binding.root
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
            val fragment = instanceOf<FaqsFragment>()
            iFragmentLifeCycleEvent.pushFragment(fragment)
        }
    }

    private fun gotoSection(category: Category) {
        val fragment = instanceOf<ProgramsFragment>(Pair(Constants.KEY_SECTION_ID, category.titleId),
                Pair(Constants.KEY_SECTION_NAME, category.titleName))
        iFragmentLifeCycleEvent.pushFragment(fragment)
    }


    private fun getTitleDataList(): ArrayList<Category> {
        val _beans = ArrayList<Category>()
        this.context?.let {
            _beans.add(Category(1, "Linked List", ContextCompat.getColor(this.context!!, R.color.colorTitle1), R.drawable.ic_dashboard_linked_list_24dp))
            _beans.add(Category(2, "Stack", ContextCompat.getColor(this.context!!, R.color.colorTitle2), R.drawable.ic_dashboard_stack_24dp))
            _beans.add(Category(3, "Queues", ContextCompat.getColor(this.context!!, R.color.colorTitle3), R.drawable.ic_dashboard_queue_24dp))

            _beans.add(Category(4, "Trees", ContextCompat.getColor(this.context!!, R.color.colorTitle4), R.drawable.ic_dashboard_tree_24dp))
            _beans.add(Category(5, "Graphs", ContextCompat.getColor(this.context!!, R.color.colorTitle5), R.drawable.ic_dashboard_graph_24dp))
            _beans.add(Category(6, "Searching", ContextCompat.getColor(this.context!!, R.color.colorTitle6), R.drawable.ic_dashboard_search_code_24dp))

            _beans.add(Category(7, "Sorting", ContextCompat.getColor(this.context!!, R.color.colorTitle7), R.drawable.ic_dashboard_sort_24dp))
            _beans.add(Category(8, "Interview questions", ContextCompat.getColor(this.context!!, R.color.colorTitle8), R.drawable.ic_dashboard_faq_24dp))
        }
        return _beans
    }
}