package com.saurabh.java.datastructure.ui.adapters

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.ui.holders.DataBoundViewHolder



/**
 * Created by Saurabh Khare on 05/28/18.
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The of the ViewDataBinding
</V></T> */
abstract class DataBoundListAdapter<T, V : ViewDataBinding>(
        appExecutors: AppExecutors,
        diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBoundViewHolder<V>>(
        AsyncDifferConfig.Builder<T>(diffCallback)
                .setBackgroundThreadExecutor(appExecutors.diskIO())
                .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position), position)
        holder.binding.executePendingBindings()
    }

    override fun submitList(list: MutableList<T>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }
    protected abstract fun bind(binding: V, item: T, position: Int)
}

