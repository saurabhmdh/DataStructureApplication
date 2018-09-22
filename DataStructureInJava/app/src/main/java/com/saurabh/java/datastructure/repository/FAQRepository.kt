package com.saurabh.java.datastructure.repository

import androidx.annotation.AnyThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.db.dao.FAQDao
import com.saurabh.java.datastructure.db.tables.FAQ
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FAQRepository @Inject constructor(private val appExecutors: AppExecutors, private val faqDao: FAQDao) {
    private val mValues: MediatorLiveData<List<FAQ>> = MediatorLiveData()

    @AnyThread
    fun getFAQs(): LiveData<List<FAQ>> {
//        val cache: LiveData<List<FAQ>> = faqDao.getAllFAQs()
//        mValues.addSource(cache) { data ->
//            if (data == null || data.isEmpty()) {
//                mValues.postValue(null)
//            } else {
//                mValues.removeSource(cache)
//                mValues.postValue(data)
//            }
//
//        }
        return faqDao.getAllFAQs()
    }

    fun update(faq: FAQ) {
        appExecutors.diskIO().execute {
            faqDao.update(faq)
        }
    }
}