package com.saurabh.java.datastructure.di

import androidx.lifecycle.ViewModelProvider
import com.saurabh.java.datastructure.viewmodel.DataStructureViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DataStructureViewModelFactory): ViewModelProvider.Factory
}