package com.saurabh.java.datastructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saurabh.java.datastructure.db.tables.FAQ

@Dao
interface FAQDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(faq: FAQ)

    @Query("SELECT * FROM ds_faqs")
    fun getAllFAQs(): LiveData<List<FAQ>>
}