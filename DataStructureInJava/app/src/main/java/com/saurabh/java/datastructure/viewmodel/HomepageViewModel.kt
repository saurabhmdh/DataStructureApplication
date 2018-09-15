package com.saurabh.java.datastructure.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saurabh.java.datastructure.db.tables.FAQTable
import javax.inject.Inject
import com.saurabh.java.datastructure.repository.FAQRepository

class HomepageViewModel @Inject constructor(private val repo: FAQRepository) : ViewModel() {
    fun getAllFaqs() : LiveData<List<FAQTable>> {
        return repo.getFAQs()
    }
}