package com.saurabh.java.datastructure.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(var titleId : Int, var titleName: String, var resTitleColor: Int, var resDrawable: Int) : Parcelable