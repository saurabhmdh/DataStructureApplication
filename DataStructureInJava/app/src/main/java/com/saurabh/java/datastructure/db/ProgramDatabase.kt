package com.saurabh.java.datastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.tables.Program

@Database(entities = [Program::class], version = 1, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase() {
    abstract fun programDao(): ProgramDao
}