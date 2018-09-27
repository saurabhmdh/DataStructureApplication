package com.saurabh.java.datastructure.di

import com.saurabh.java.datastructure.ui.fragments.*
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

    @ContributesAndroidInjector
    abstract fun contributeQuestionCardFragment(): QuestionCardFragment

    @ContributesAndroidInjector
    abstract fun contributeFavouriteFragment(): FavouriteFragment
}