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

    fun setValue(aKey: String, aValue: String) {
        mPref.edit().putString(aKey, aValue).apply()
    }

    fun clear(): Boolean {
        return mPref.edit().clear().commit()
    }
}