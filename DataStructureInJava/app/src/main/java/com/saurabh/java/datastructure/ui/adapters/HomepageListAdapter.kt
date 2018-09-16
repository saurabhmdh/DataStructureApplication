package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.RowItemListviewHomeBinding
import com.saurabh.java.datastructure.vo.Category

class HomepageListAdapter(
        private val dataBindingComponent: FragmentDataBindingComponent,
        private val callback: CategoryClickCallback)
    : DataBoundListAdapter<Category, RowItemListviewHomeBinding>() {

    override fun createBinding(parent: ViewGroup): RowItemListviewHomeBinding{
        val binding = DataBindingUtil
                .inflate<RowItemListviewHomeBinding>(LayoutInflater.from(parent.context),
                        R.layout.row_item_listview_home, parent, false,
                        dataBindingComponent)
        binding.llRowContainer.setOnClickListener { _ ->
            binding.category?.let {category ->
                callback.onClick(category)
            }
        }
        return binding
    }

    override fun bind(binding: RowItemListviewHomeBinding, item: Category, isLast: Boolean) {
        binding.category = item
    }

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    interface CategoryClickCallback {
        fun onClick(category: Category)
    }
}