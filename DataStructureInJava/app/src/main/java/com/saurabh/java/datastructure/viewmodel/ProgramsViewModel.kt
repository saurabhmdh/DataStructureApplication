package com.saurabh.java.datastructure.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.repository.ProgramRepository
import javax.inject.Inject

class ProgramsViewModel @Inject constructor(private val repo: ProgramRepository) : ViewModel() {
    fun getAllProgramsByCategory(category: String): LiveData<List<Program>> {
        return repo.getAllProgramsByCategory(category)
    }

    fun updateProgram(program: Program) {
        repo.updateProgram(program)
    }

    fun getAllFavorite(): LiveData<List<Program>> {
        return repo.getAllFavorites()
    }
}