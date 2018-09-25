package com.saurabh.java.datastructure.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.databinding.ActivityQuestionCardBinding
import com.saurabh.java.datastructure.db.tables.FAQ
import com.saurabh.java.datastructure.ui.adapters.QuestionCardViewPagerAdapter
import com.saurabh.java.datastructure.viewmodel.FAQViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class QuestionCardActivity: AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var binding: ActivityQuestionCardBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var faqVM : FAQViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_question_card)
        setupViewModel()
    }

    private fun setupViewModel() {
        faqVM = ViewModelProviders.of(this, viewModelFactory).get(FAQViewModel::class.java)
        faqVM.getAllFaqs().observe(this, Observer {data ->
            data?.let {
                initQuestionCardViewPager(it)
            }
        })
    }

    private fun initQuestionCardViewPager(item: List<FAQ>) {
        val adapter = QuestionCardViewPagerAdapter(item, supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = 1
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.setPageTransformer(false) { page, position ->
            val normalizedPosition = Math.abs(Math.abs(position) - 1)
            page.scaleX = normalizedPosition / 2 + 0.5f
            page.scaleY = normalizedPosition / 2 + 0.5f
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}