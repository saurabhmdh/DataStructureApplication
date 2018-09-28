package com.saurabh.java.datastructure.util.services

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import com.saurabh.java.datastructure.BuildConfig
import com.saurabh.java.datastructure.DataStructureApplication
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.sqlasset.DatabaseCopier
import com.saurabh.java.datastructure.di.DaggerAppComponent
import com.saurabh.java.datastructure.util.LookupTable
import com.saurabh.java.datastructure.util.PrefManager
import timber.log.Timber
import javax.inject.Inject

class StartUpService @Inject constructor() {
    fun start() {
        val backGroundWorker = OneTimeWorkRequestBuilder<BackGroundWorker>().build()
        WorkManager.getInstance().enqueue(backGroundWorker)
    }
}

class BackGroundWorker : Worker() {
    @Inject
    lateinit var prefManager: PrefManager

    @Inject
    lateinit var dao: ProgramDao

    @Inject
    lateinit var lookupTable: LookupTable

    override fun doWork(): Result {
        DaggerAppComponent.builder().application(applicationContext as DataStructureApplication).build().inject(this)

        if (prefManager.getInt(Constants.KEY_VERSION_CODE) != BuildConfig.VERSION_CODE) {
            //First launch so need to populate database.
            ProgramDataManager(dao, lookupTable, applicationContext)
            val name = "ds_droid.db"
            DatabaseCopier.copyAttachedDatabase(applicationContext, name)
            prefManager.setInt(Constants.KEY_VERSION_CODE, BuildConfig.VERSION_CODE)
        }

        return Result.SUCCESS
    }

}