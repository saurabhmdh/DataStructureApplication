package com.saurabh.java.datastructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saurabh.java.datastructure.db.tables.FAQ

@Dao
interface FAQDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(faq: FAQ)

    @Query("SELECT * FROM ds_faqs")
    fun getAllFAQs(): LiveData<List<FAQ>>

    @Update
    fun update(faq: FAQ)
}