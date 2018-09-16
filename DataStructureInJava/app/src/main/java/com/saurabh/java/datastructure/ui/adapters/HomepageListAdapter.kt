package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.RowItemListviewHomeBinding
import com.saurabh.java.datastructure.vo.Category
import timber.log.Timber

class HomepageListAdapter(private val dataBindingComponent: FragmentDataBindingComponent) : DataBoundListAdapter<Category, RowItemListviewHomeBinding>() {

    override fun createBinding(parent: ViewGroup): RowItemListviewHomeBinding{
        val binding = DataBindingUtil
                .inflate<RowItemListviewHomeBinding>(LayoutInflater.from(parent.context),
                        R.layout.row_item_listview_home, parent, false,
                        dataBindingComponent)
        return binding
    }

    override fun bind(binding: RowItemListviewHomeBinding, item: Category, isLast: Boolean) {
        binding.tvTitle.text = item.titleName
        Timber.i("onBind ${item.titleName} ${item.resTitleColor}")
        binding.ivGridIcon.setImageResource(item.resDrawable)
    }

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}