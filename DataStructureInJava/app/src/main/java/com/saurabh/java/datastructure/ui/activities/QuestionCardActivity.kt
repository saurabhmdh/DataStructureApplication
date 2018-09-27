package com.saurabh.java.datastructure.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
        setupUIComponents()
    }

    private fun setupUIComponents() {
       val imageView = findViewById<AppCompatImageView>(R.id.iv_back)
        imageView.setOnClickListener {
            finish()
        }

        //TODO: Share functionality to be added
//        val shareView = findViewById<AppCompatImageView>(R.id.iv_share)
//        shareView.setOnClickListener {
//
//        }
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
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.setPageTransformer(false) { page, position ->
            val normalizedPosition = Math.abs(Math.abs(position) - 1)
            page.scaleX = normalizedPosition / 2 + 0.5f
            page.scaleY = normalizedPosition / 2 + 0.5f
        }
    }

    fun updateQuestionNumber() {
        val result : Int = (binding.viewPager.currentItem + 1)
        binding.tvQuestionCardNumber.text = result.toString()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}