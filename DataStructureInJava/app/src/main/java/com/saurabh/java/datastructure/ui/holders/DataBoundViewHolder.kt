package com.saurabh.java.datastructure.ui.holders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Saurabh Khare on 05/28/18.
 * A generic ViewHolder that works with a [ViewDataBinding].
 *
 * @param <T> The type of the ViewDataBinding.
</T> */
class DataBoundViewHolder<T : ViewDataBinding> internal constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)