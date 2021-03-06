package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.AdapterRowItemFaqsBinding
import com.saurabh.java.datastructure.db.tables.FAQ


class FAQsListAdapter(
        private val dataBindingComponent: FragmentDataBindingComponent,
        appExecutors: AppExecutors,
        private val callback: FAQsListClickCallback)
    : DataBoundListAdapter<FAQ, AdapterRowItemFaqsBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<FAQ>(){
            override fun areItemsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
                return oldItem == newItem
            }

        }
    ) {


    override fun createBinding(parent: ViewGroup): AdapterRowItemFaqsBinding {

        return DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),
                        R.layout.adapter_row_item_faqs, parent, false,
                        dataBindingComponent)
    }
    override fun bind(binding: AdapterRowItemFaqsBinding, item: FAQ, position: Int) {
        binding.faqsBean = item
        binding.ivMoreOrLess.setOnClickListener{
            binding.faqsBean?.let {faq ->
                if (faq.isOpen == 0) {
                    binding.ivMoreOrLess.setImageResource(R.drawable.ic_up_arrow)
                    binding.llAnswer.visibility = View.VISIBLE
                } else {
                    binding.ivMoreOrLess.setImageResource(R.drawable.ic_down_arrow)
                    binding.llAnswer.visibility = View.GONE
                }
                callback.onClick(faq, position)
            }
        }
    }

    interface FAQsListClickCallback {
        fun onClick(faq: FAQ, position: Int)
    }
}