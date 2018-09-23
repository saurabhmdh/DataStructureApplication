package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.saurabh.java.datastructure.interfaces.IActionBarTitleHandler
import com.saurabh.java.datastructure.interfaces.IFragmentLifeCycleEvent
import com.saurabh.java.datastructure.vo.ActionbarItem

abstract class BaseFragment : Fragment() {

    private lateinit var iFragmentLifeCycleEvent: IFragmentLifeCycleEvent
    private lateinit var iActionBarTitleHandler: IActionBarTitleHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IFragmentLifeCycleEvent) {
            iFragmentLifeCycleEvent = context
        }
        if (context is IActionBarTitleHandler) {
            iActionBarTitleHandler = context
        }
    }

    open fun pushFragment(fragment: Fragment) {
        iFragmentLifeCycleEvent.pushFragment(fragment)
    }

    abstract fun getTitle() : ActionbarItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item: ActionbarItem = getTitle()
        iActionBarTitleHandler.updateActionBarTitle(item)
    }
}