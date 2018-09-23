package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.databinding.FragmentProgramsBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.util.autoCleared

import com.saurabh.java.datastructure.viewmodel.ProgramsViewModel
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category
import timber.log.Timber
import javax.inject.Inject

class ProgramsFragment : BaseFragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentProgramsBinding>()
    var category : Category? = null
    lateinit var viewmodel : ProgramsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val bundle = this.arguments
        this.category = bundle?.getParcelable(Constants.BUNDLE_OBJECT) as Category
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentProgramsBinding>(inflater, R.layout.fragment_programs, container, false, dataBindingComponent)
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
        setupViewModel()
    }

    private fun setupViewModel() {
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(ProgramsViewModel::class.java)
        viewmodel.getAllPrograms().observe(this, Observer {programs ->
            programs?.let {
                for (data in programs) {
                    Timber.i("saurabh data from DB = ${data}")
                }
            }

        })
    }
}