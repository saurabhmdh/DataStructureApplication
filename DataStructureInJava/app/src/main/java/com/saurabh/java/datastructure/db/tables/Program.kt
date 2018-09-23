package com.saurabh.java.datastructure.db.tables

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.saurabh.java.datastructure.constants.Constants
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ds_tbl_programs")
@JvmSuppressWildcards
@Parcelize
data class Program(
        @ColumnInfo(name = "program_id")
        var programId: Int,

        @ColumnInfo(name = "program_title")
        var programTitle: String,

        @ColumnInfo(name = "category_name")
        var categoryName: String,

        @ColumnInfo(name = "program_path")
        var programPath: String,

        @ColumnInfo(name = "is_favourite")
        var favourite: Int = 0,

        @ColumnInfo(name = "program_code")
        var programCode: String = Constants.EMPTY_STRING
): Parcelable {
        @IgnoredOnParcel
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Int = 0
}