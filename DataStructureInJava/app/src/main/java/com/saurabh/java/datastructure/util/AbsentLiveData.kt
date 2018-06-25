package com.saurabh.java.datastructure.util

import android.arch.lifecycle.LiveData

/**
 * Created by saurabh.khare on 2018/06/25.
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}