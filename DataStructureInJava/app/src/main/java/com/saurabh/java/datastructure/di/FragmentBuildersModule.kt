package com.saurabh.java.datastructure.di

import com.saurabh.java.datastructure.ui.fragments.DisplayProgramFragment
import com.saurabh.java.datastructure.ui.fragments.FaqsFragment
import com.saurabh.java.datastructure.ui.fragments.HomePageFragment
import com.saurabh.java.datastructure.ui.fragments.ProgramsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomePageFragment(): HomePageFragment

    @ContributesAndroidInjector
    abstract fun contributeFaqsFragment(): FaqsFragment

    @ContributesAndroidInjector
    abstract fun contributeProgramsFragment(): ProgramsFragment

    @ContributesAndroidInjector
    abstract fun contributeDisplayProgramFragment(): DisplayProgramFragment
}