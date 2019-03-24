package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.AdapterRowCategoryOptionBinding
import com.saurabh.java.datastructure.databinding.FragmentFaqsBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.adapters.DataBoundListAdapter
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category
import javax.inject.Inject

/* It should display same category actionbar as parent fragment */
class CategoryOptionsFragment: BaseFragment(), Injectable {

    var dataBinding by autoCleared<FragmentFaqsBinding>()
    private val dataBindingComponent = FragmentDataBindingComponent(this)

    var category : Category? = null
    private var adapter by autoCleared<CategoryOptionsAdapter>()

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentFaqsBinding.inflate(inflater, container,false, dataBindingComponent)
        return dataBinding.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let { bundle ->
            val args = CategoryOptionsFragmentArgs.fromBundle(bundle)
            this.category = args.bundleObject
        }
    }

    //It should get title from bundle object.
    override fun getTitle(): ActionbarItem {
        category?.let {
            return ActionbarItem(it.titleName, it.resDrawable)
        }
        //TODO: Handle favorite alone.
        return ActionbarItem(getString(R.string.favourite), R.drawable.ic_favorite_color)
    }
    //It should have 3 categories.
    //One adapter who categories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.recyclerviewFaqs.layoutManager = LinearLayoutManager(activity)
        dataBinding.recyclerviewFaqs.setHasFixedSize(true)
        adapter = CategoryOptionsAdapter(dataBindingComponent, appExecutors,
                object : CategoryOptionsAdapter.CategoryOptionsClickCallback{
                    override fun onClick(data: String, position: Int) {
                       //Timber.i("Saurabh " + position)
                    }

                })
    }

    class CategoryOptionsAdapter (private val dataBindingComponent: FragmentDataBindingComponent,
                                  appExecutors: AppExecutors,
                                  private val callback: CategoryOptionsClickCallback)
        : DataBoundListAdapter<String, AdapterRowCategoryOptionBinding>(appExecutors,
            diffCallback = object :  DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

            }) {
        override fun createBinding(parent: ViewGroup): AdapterRowCategoryOptionBinding {
            val binding = AdapterRowCategoryOptionBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false, dataBindingComponent)

            return binding
        }

        override fun bind(binding: AdapterRowCategoryOptionBinding, item: String, position: Int) {
            binding.data = item
        }

        interface CategoryOptionsClickCallback {
            fun onClick(data: String, position: Int)
        }
    }
}