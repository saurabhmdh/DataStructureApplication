package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentDisplayProgramBinding
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category

class DisplayProgramFragment: BaseFragment(), Injectable {
    var category : Category? = null
    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var program : Program? = null

    var dataBinding by autoCleared<FragmentDisplayProgramBinding>()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let { bundle ->
            val args = DisplayProgramFragmentArgs.fromBundle(bundle)
            this.category = args.bundleObject
            this.program = args.bundleProgram
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentDisplayProgramBinding.inflate(inflater, container, false, dataBindingComponent)
        return dataBinding.root
    }

    override fun getTitle(): ActionbarItem {
        category?.let {
            return ActionbarItem(it.titleName, it.resDrawable)
        }
        return ActionbarItem(getString(R.string.favourite), R.drawable.ic_favorite_color)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.program = program
    }
}