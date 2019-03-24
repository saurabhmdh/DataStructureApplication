package com.saurabh.java.datastructure.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.saurabh.java.datastructure.interfaces.IActionBarTitleHandler
import com.saurabh.java.datastructure.vo.ActionbarItem

abstract class BaseFragment : Fragment() {

    private var iActionBarTitleHandler: IActionBarTitleHandler? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IActionBarTitleHandler) {
            iActionBarTitleHandler = context
        }
    }

    abstract fun getTitle() : ActionbarItem

    override fun onResume() {
        super.onResume()
        val item: ActionbarItem = getTitle()
        iActionBarTitleHandler?.updateActionBarTitle(item)
    }
}