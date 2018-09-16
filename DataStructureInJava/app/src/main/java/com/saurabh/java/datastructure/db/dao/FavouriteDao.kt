package com.saurabh.java.datastructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saurabh.java.datastructure.db.tables.Favourite


@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fav: Favourite)

    @Query("DELETE FROM ds_favourite_progs WHERE `id` = :index")
    fun getData(index: Int): LiveData<Favourite>
}