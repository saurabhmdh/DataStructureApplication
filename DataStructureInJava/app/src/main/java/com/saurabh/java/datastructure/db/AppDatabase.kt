package com.saurabh.java.datastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saurabh.java.datastructure.db.dao.FAQDao
import com.saurabh.java.datastructure.db.tables.FAQTable

@Database(entities = [FAQTable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun faqdao(): FAQDao
}