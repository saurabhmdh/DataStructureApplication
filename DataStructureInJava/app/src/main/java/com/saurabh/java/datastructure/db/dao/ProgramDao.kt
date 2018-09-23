package com.saurabh.java.datastructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saurabh.java.datastructure.db.tables.Program

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(program: Program)

    @Insert
    fun insertAll(programs: ArrayList<Program>)

    @Query("SELECT * FROM ds_tbl_programs")
    fun getAllPrograms(): LiveData<List<Program>>

    @Update
    fun update(program: Program)
}