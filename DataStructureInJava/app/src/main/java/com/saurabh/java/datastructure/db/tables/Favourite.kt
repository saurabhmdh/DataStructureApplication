package com.saurabh.java.datastructure.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ds_favourite_progs")
class Favourite (
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var _id: Int,

    @ColumnInfo(name = "program_id")
    var programId: Int,

    @ColumnInfo(name = "category_name")
    var categoryName: String,

    @ColumnInfo(name = "program_path")
    var programPath: String,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean,

    @ColumnInfo(name = "program_code")
    var programCode: String
)