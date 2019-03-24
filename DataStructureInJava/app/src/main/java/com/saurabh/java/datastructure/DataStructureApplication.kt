package com.saurabh.java.datastructure

import android.app.Activity
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.saurabh.java.datastructure.di.AppInjector
import com.saurabh.java.datastructure.di.DaggerAwareWorkerFactory
import com.saurabh.java.datastructure.util.services.StartUpService
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by saurabh.khare on 2018/06/25.
 */

class DataStructureApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var startUpService: StartUpService

    @Inject lateinit var daggerAwareWorkerFactory: DaggerAwareWorkerFactory

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
        configureWorkManager()
        startUpService.start()
    }

    private fun configureWorkManager() {
        val config = Configuration.Builder()
                .setWorkerFactory(daggerAwareWorkerFactory)
                .build()
        WorkManager.initialize(this, config)
    }
    override fun activityInjector() = dispatchingAndroidInjector
}