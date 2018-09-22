package com.saurabh.java.datastructure.interfaces

import androidx.fragment.app.Fragment

interface IFragmentLifeCycleEvent {
    fun pushFragment(fragment: Fragment)
    fun popFragment(): Boolean
}