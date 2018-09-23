package com.saurabh.java.datastructure.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saurabh.java.datastructure.viewmodel.DataStructureViewModelFactory
import com.saurabh.java.datastructure.viewmodel.FAQViewModel
import com.saurabh.java.datastructure.viewmodel.ProgramsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DataStructureViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FAQViewModel::class)
    abstract fun bindHomepageViewModel(faqViewModel: FAQViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProgramsViewModel::class)
    abstract fun bindProgramsViewModel(programsViewModel: ProgramsViewModel): ViewModel
}