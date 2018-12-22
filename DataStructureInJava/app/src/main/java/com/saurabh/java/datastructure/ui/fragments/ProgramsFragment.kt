package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentProgramsBinding
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.adapters.ProgramListAdapter
import com.saurabh.java.datastructure.util.autoCleared

import com.saurabh.java.datastructure.viewmodel.ProgramsViewModel
import com.saurabh.java.datastructure.vo.ActionbarItem
import com.saurabh.java.datastructure.vo.Category
import javax.inject.Inject

class ProgramsFragment : BaseFragment(), Injectable {
    private val dataBindingComponent = FragmentDataBindingComponent(this)

    var dataBinding by autoCleared<FragmentProgramsBinding>()
    var category : Category? = null

    lateinit var viewmodel : ProgramsViewModel
    private var adapter by autoCleared<ProgramListAdapter>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let {bundle ->
            val args = ProgramsFragmentArgs.fromBundle(bundle)
            this.category = args.bundleObject
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentProgramsBinding.inflate(inflater, container, false, dataBindingComponent)
        return dataBinding.root
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
        setupList()
    }

    private fun setupList() {
        dataBinding.recyclerViewPrograms.layoutManager = LinearLayoutManager(activity)
        adapter = ProgramListAdapter(dataBindingComponent, appExecutors, {program ->
            Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                    .navigate(ProgramsFragmentDirections.actionNavigateDisplayProgram(category!!, program)) },
            { view, program ->
                run {
                    handleFavorite(view, program)
                }
            } )
        dataBinding.recyclerViewPrograms.adapter = adapter
    }

    private fun handleFavorite(view: View, program: Program) {
        val button: ToggleButton = view as ToggleButton
        program.favourite = if (button.isChecked) 1 else 0
        viewmodel.updateProgram(program)
    }

    private fun setupViewModel() {
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(ProgramsViewModel::class.java)
        category?.let { category1 ->
            viewmodel.getAllProgramsByCategory(category1.dirPath).observe(this, Observer {programs ->
                adapter.submitList(programs?.toMutableList())
            })
        }

    }
}