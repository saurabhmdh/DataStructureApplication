package com.saurabh.java.datastructure.util.services

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import javax.inject.Inject

class StartUpService @Inject constructor() {
    fun start() {
        val backGroundWorker = OneTimeWorkRequestBuilder<BackGroundWorker>().build()
        WorkManager.getInstance().enqueue(backGroundWorker)
    }
}

