package com.saurabh.java.datastructure.repository

import androidx.lifecycle.LiveData
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.tables.Program
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramRepository @Inject constructor(private val appExecutors: AppExecutors, private val progDao: ProgramDao) {
    fun getAllPrograms(): LiveData<List<Program>> {
        return progDao.getAllPrograms()
    }
}