package com.saurabh.java.datastructure.di

import androidx.work.ListenableWorker
import com.saurabh.java.datastructure.interfaces.IWorkerFactory
import com.saurabh.java.datastructure.util.services.BackGroundWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(BackGroundWorker::class)
    fun bindHelloWorker(factory: BackGroundWorker.Factory): IWorkerFactory<out ListenableWorker>

    //Every time when we add a worker, add a binding here


}