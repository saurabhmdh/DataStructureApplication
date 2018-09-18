package com.saurabh.java.datastructure.di

import com.saurabh.java.datastructure.ui.activities.Homepage
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by saurabh.khare on 2018/06/25.
 */

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): Homepage
}
