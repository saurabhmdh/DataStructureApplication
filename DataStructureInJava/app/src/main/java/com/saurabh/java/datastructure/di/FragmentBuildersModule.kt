package com.saurabh.java.datastructure.di

import com.saurabh.java.datastructure.ui.fragments.HomePageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomePageFragment(): HomePageFragment
}