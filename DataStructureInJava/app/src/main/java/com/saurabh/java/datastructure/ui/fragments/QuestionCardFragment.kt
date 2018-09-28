package com.saurabh.java.datastructure.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.bindings.FragmentDataBindingComponent
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.databinding.FragmentQuestionCardBinding
import com.saurabh.java.datastructure.db.tables.FAQ
import com.saurabh.java.datastructure.di.Injectable
import com.saurabh.java.datastructure.ui.activities.QuestionCardActivity
import com.saurabh.java.datastructure.util.autoCleared
import com.saurabh.java.datastructure.vo.ActionbarItem

class QuestionCardFragment: BaseFragment(), Injectable {

    private val dataBindingComponent = FragmentDataBindingComponent(this)
    var dataBinding by autoCleared<FragmentQuestionCardBinding>()

    private var position: Int = 0
    private var faq: FAQ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentQuestionCardBinding>(inflater, R.layout.fragment_question_card, container, false, dataBindingComponent)
        dataBinding = binding
        return binding.root
    }
    override fun getTitle(): ActionbarItem {
        return ActionbarItem()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.tvQuestionAnswer.movementMethod = ScrollingMovementMethod()
        
        faq?.let {
            dataBinding.tvFragmentTitle.text = it.question
            dataBinding.tvQuestionAnswer.text = it.answer
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(Constants.BUNDLE_POSITION) ?: 0
        faq = arguments?.getParcelable(Constants.BUNDLE_OBJECT)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setQuestionNumberIndicatorValue()
        }
    }

    private fun setQuestionNumberIndicatorValue() {
        Handler().postDelayed({
            (activity as QuestionCardActivity).updateQuestionNumber()
        }, 5)
    }
}