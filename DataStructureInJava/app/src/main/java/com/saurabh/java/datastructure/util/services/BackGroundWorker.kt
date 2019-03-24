package com.saurabh.java.datastructure.util.services

import android.app.Application
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.saurabh.java.datastructure.BuildConfig
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.sqlasset.DatabaseCopier
import com.saurabh.java.datastructure.interfaces.IWorkerFactory
import com.saurabh.java.datastructure.util.LookupTable
import com.saurabh.java.datastructure.util.PrefManager
import javax.inject.Inject


class BackGroundWorker(context: Context, params: WorkerParameters,
                       private val prefManager: PrefManager,
                       val dao: ProgramDao,
                       val lookupTable: LookupTable) : Worker(context, params) {

    override fun doWork(): Result {
        if (prefManager.getInt(Constants.KEY_VERSION_CODE) != BuildConfig.VERSION_CODE) {
            //First launch so need to populate database.
            ProgramDataManager(dao, lookupTable, applicationContext)
            val name = "ds_droid.db"
            DatabaseCopier.copyAttachedDatabase(applicationContext, name)
            prefManager.setInt(Constants.KEY_VERSION_CODE, BuildConfig.VERSION_CODE)
        }
        return Result.success()
    }

    /* Factory implementation for Work Manager */
    class Factory @Inject constructor(
            private val prefManager: PrefManager,
            val dao: ProgramDao,
            val lookupTable: LookupTable,
            val application: Application)
        : IWorkerFactory<BackGroundWorker> {

        override fun create(params: WorkerParameters): BackGroundWorker {
            return BackGroundWorker(application, params, prefManager, dao, lookupTable)
        }
    }
}