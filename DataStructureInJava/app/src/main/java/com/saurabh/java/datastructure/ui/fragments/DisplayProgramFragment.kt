package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
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
        val bundle = this.arguments
        this.category = bundle?.getParcelable(Constants.BUNDLE_OBJECT) as Category
        this.program = bundle.getParcelable(Constants.BUNDLE_OBJECT_PROGRAM) as Program
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDisplayProgramBinding>(inflater, R.layout.fragment_display_program, container, false, dataBindingComponent)
        dataBinding = binding
        return binding.root
    }

    override fun getTitle(): ActionbarItem {
        category?.let {
            return ActionbarItem(it.titleName, it.resDrawable)
        }
        return ActionbarItem()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.program = program
    }
}