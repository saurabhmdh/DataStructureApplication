package com.saurabh.java.datastructure.util

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Singleton

@Singleton
class PrefManager(var context: Application) {

    private val mPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getValue(key: String): String? {
        return mPref.getString(key, null)
    }

    fun getInt(key: String): Int {
        return mPref.getInt(key, 0)
    }

    fun setValue(aKey: String, aValue: String) {
        mPref.edit().putString(aKey, aValue).apply()
    }

    fun setInt(key: String, value: Int) {
        mPref.edit().putInt(key, value).apply()
    }

    fun clear(): Boolean {
        return mPref.edit().clear().commit()
    }
}