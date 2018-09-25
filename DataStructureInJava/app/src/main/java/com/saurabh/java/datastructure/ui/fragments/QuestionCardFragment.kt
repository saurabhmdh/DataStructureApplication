package com.saurabh.java.datastructure.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.databinding.FragmentQuestionCardBinding
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.ActionbarItem

class QuestionCardFragment: BaseFragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentQuestionCardBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentQuestionCardBinding>(inflater, R.layout.fragment_question_card, container, false, dataBindingComponent)
        dataBinding = binding
        return binding.root

    }
    override fun getTitle(): ActionbarItem {
        return ActionbarItem()
    }
}