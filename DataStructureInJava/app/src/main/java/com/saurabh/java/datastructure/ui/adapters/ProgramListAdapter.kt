package com.saurabh.java.datastructure.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.databinding.AdapterRowItemProgramBinding
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.util.Utils

class ProgramListAdapter (private val dataBindingComponent: FragmentDataBindingComponent,
                          appExecutors: AppExecutors,
                          private val callback: ((Program) -> Unit)?,
                          private val toggleFav: ((View, Program) -> Unit)?
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
        binding.llMainContent.setOnClickListener{
            binding.program?.let {
                callback?.invoke(it)
            }
        }
        binding.tbFavorite.setOnClickListener {view ->
            binding.program?.let {
                toggleFav?.invoke(view, it)
            }
        }
        binding.ivCopy.setOnClickListener {
            val code = binding?.program?.programCode ?: Constants.EMPTY_STRING
            Snackbar.make( binding.ivCopy, "Copied to clipboard", Snackbar.LENGTH_SHORT).show()
            Utils.copyToClipboard(parent.context, code)
        }
        binding.ivShare.setOnClickListener {
            val code = binding?.program?.programCode ?: Constants.EMPTY_STRING
            Utils.shareCodesViaApps(parent.context, code)
        }
        return binding
    }

    override fun bind(binding: AdapterRowItemProgramBinding, item: Program, position: Int) {
        binding.program = item
    }
}