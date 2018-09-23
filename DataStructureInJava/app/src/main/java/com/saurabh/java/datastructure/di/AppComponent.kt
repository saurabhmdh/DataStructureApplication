package com.saurabh.java.datastructure.di

import android.app.Application
import com.saurabh.java.datastructure.DataStructureApplication
import com.saurabh.java.datastructure.util.services.BackGroundWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            MainActivityModule::class]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: DataStructureApplication)
    fun inject(worker: BackGroundWorker)
}