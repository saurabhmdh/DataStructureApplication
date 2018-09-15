package com.saurabh.java.datastructure.db.tables

import androidx.room.*

@Entity(tableName = "ds_faqs")
@JvmSuppressWildcards
class FAQTable (
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var _id: Int,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "answer")
    var answer: String,

    @ColumnInfo(name = "is_favourite")
    var favourite: Boolean
)