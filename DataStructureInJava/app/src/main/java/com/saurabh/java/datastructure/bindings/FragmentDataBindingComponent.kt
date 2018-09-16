package com.saurabh.java.datastructure.bindings

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingComponent
/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}