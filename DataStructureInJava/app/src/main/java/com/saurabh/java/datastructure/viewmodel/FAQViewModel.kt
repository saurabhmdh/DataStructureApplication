package com.saurabh.java.datastructure.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saurabh.java.datastructure.db.tables.FAQ
import com.saurabh.java.datastructure.repository.FAQRepository
import javax.inject.Inject

class FAQViewModel @Inject constructor(private val repo: FAQRepository) : ViewModel() {

    fun getAllFaqs() : LiveData<List<FAQ>> {
        return repo.getFAQs()
    }

    fun updateFAQ(faq: FAQ) {
        repo.update(faq)
    }
}