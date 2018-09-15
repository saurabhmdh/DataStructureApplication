package com.saurabh.java.datastructure.util.services

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import javax.inject.Inject

class StartUpService @Inject constructor() {
    fun start() {
        val backGroundWorker = OneTimeWorkRequestBuilder<BackGroundWorker>().build()
        WorkManager.getInstance().enqueue(backGroundWorker)
    }
}

class BackGroundWorker : Worker() {
    override fun doWork(): Result {
        return Result.SUCCESS
    }

}