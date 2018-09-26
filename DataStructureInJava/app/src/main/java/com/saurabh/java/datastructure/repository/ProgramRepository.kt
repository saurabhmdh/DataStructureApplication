package com.saurabh.java.datastructure.repository

import androidx.lifecycle.LiveData
import com.saurabh.java.datastructure.AppExecutors
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.tables.Program
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgramRepository @Inject constructor(private val appExecutors: AppExecutors, private val progDao: ProgramDao) {

    fun getAllProgramsByCategory(category: String): LiveData<List<Program>> {
        return progDao.getAllProgramsByCategory(category)
    }

    fun updateProgram(program: Program) {
        appExecutors.networkIO().execute {
            progDao.update(program)
        }
    }
}