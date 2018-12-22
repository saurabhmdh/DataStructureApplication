package com.saurabh.java.datastructure.db.tables

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ds_faqs")
@JvmSuppressWildcards
@Parcelize
data class FAQ (
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var _id: Int,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "is_favourite")
    var favourite: Int,

    @ColumnInfo(name = "is_open")
    var isOpen: Int
) : Parcelable