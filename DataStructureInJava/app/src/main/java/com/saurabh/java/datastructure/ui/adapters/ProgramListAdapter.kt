package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.AdapterRowItemProgramBinding
import com.saurabh.java.datastructure.db.tables.Program

class ProgramListAdapter (private val dataBindingComponent: FragmentDataBindingComponent,
                         appExecutors: AppExecutors,
                          private val callback: ((Program) -> Unit)?
) : DataBoundListAdapter<Program, AdapterRowItemProgramBinding>(appExecutors,
        diffCallback = object :  DiffUtil.ItemCallback<Program>() {
            override fun areItemsTheSame(oldItem: Program, newItem: Program): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Program, newItem: Program): Boolean {
                return oldItem == newItem
            }

        }) {

    override fun createBinding(parent: ViewGroup): AdapterRowItemProgramBinding {
        val binding = DataBindingUtil
                .inflate<AdapterRowItemProgramBinding>(LayoutInflater.from(parent.context),
                        R.layout.adapter_row_item_program, parent, false,
                        dataBindingComponent)
        binding.programCardView.setOnClickListener{ _ ->
            binding.program?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: AdapterRowItemProgramBinding, item: Program, position: Int) {
        binding.program = item
    }
}