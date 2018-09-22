package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.AdapterRowItemListviewHomeBinding
import com.saurabh.java.datastructure.vo.Category

class HomepageListAdapter(
        private val dataBindingComponent: FragmentDataBindingComponent,
        private val appExecutors: AppExecutors,
        private val callback: CategoryClickCallback)
    : DataBoundListAdapter<Category, AdapterRowItemListviewHomeBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.equals(newItem)
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.equals(newItem)
            }

        }) {

    override fun createBinding(parent: ViewGroup): AdapterRowItemListviewHomeBinding{
        val binding = DataBindingUtil
                .inflate<AdapterRowItemListviewHomeBinding>(LayoutInflater.from(parent.context),
                        R.layout.adapter_row_item_listview_home, parent, false,
                        dataBindingComponent)
        binding.llRowContainer.setOnClickListener { _ ->
            binding.category?.let {category ->
                callback.onClick(category)
            }
        }
        return binding
    }

    override fun bind(binding: AdapterRowItemListviewHomeBinding, item: Category, position: Int) {
        binding.category = item
    }

    interface CategoryClickCallback {
        fun onClick(category: Category)
    }
}