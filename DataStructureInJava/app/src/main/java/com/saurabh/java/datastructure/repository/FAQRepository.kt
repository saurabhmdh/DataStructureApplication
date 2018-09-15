package com.saurabh.java.datastructure.repository

import androidx.annotation.AnyThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.db.dao.FAQDao
import com.saurabh.java.datastructure.db.tables.FAQTable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FAQRepository @Inject constructor(private val appExecutors: AppExecutors, private val faqDao: FAQDao) {
    private val mValues: MediatorLiveData<List<FAQTable>> = MediatorLiveData()

    @AnyThread
    fun getFAQs(): LiveData<List<FAQTable>> {
        appExecutors.diskIO().execute {         val cache: LiveData<List<FAQTable>> = faqDao.getAllFAQs()
            mValues.addSource(cache) { data ->
                if (data == null || data.isEmpty()) {
                    mValues.postValue(null)
                } else {
                    mValues.removeSource(cache)
                    mValues.postValue(data)
                }

            }
        }
        return mValues
    }
}