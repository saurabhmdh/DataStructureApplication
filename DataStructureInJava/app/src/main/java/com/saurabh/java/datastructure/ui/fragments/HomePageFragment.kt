package com.saurabh.java.datastructure.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.databinding.FragmentHomeBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.adapters.HomepageListAdapter
import com.saurabh.java.datastructure.util.LookupTable
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.util.instanceOf
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category
import timber.log.Timber
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
            pushFragment(fragment)
        }
    }

    private fun gotoSection(category: Category) {
        val fragment = instanceOf<ProgramsFragment>(Pair(Constants.BUNDLE_KEY, category.titleId),
                Pair(Constants.BUNDLE_OBJECT, category))
        pushFragment(fragment)
    }


    private fun getTitleDataList(): ArrayList<Category> {
        return lookupTable.getAllCategory()
    }

    override fun getTitle(): ActionbarItem {
        return ActionbarItem(getString(R.string.app_name), R.drawable.ic_home)
    }

}