package com.saurabh.java.datastructure.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saurabh.java.datastructure.viewmodel.DataStructureViewModelFactory
import com.saurabh.java.datastructure.viewmodel.HomepageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DataStructureViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomepageViewModel::class)
    abstract fun bindHomepageViewModel(homepageViewModel: HomepageViewModel): ViewModel
}