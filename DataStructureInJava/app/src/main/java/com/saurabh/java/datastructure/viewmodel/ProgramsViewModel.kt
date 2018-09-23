package com.saurabh.java.datastructure.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.repository.ProgramRepository
import javax.inject.Inject

class ProgramsViewModel @Inject constructor(val repo: ProgramRepository) : ViewModel() {
    fun getAllPrograms(): LiveData<List<Program>> {
        return repo.getAllPrograms()
    }
}