package com.saurabh.java.datastructure.util

import android.app.Application
import com.saurabh.java.datastructure.R
import com.saurabh.java.datastructure.vo.Category
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LookupTable @Inject constructor(var application: Application) {
    private val cacheData = ConcurrentHashMap<Int, Category>()

    init {
        if (!isValidData()) {
            throw IllegalArgumentException("arrays.xml entries are mismatch. Please correct entry")
        }

        for (item in 0 until getCategorySize() step 1) {
            val category = Category(item, application.resources.getStringArray(R.array.array_dir_name)[item],
                    application.resources.getStringArray(R.array.array_section_display_name)[item],
                    application.resources.getIntArray(R.array.array_category_colors)[item],
                    application.resources.obtainTypedArray(R.array.array_category_logo_drawables).getResourceId(item, 0))
            cacheData[item] = category
        }
    }

    private fun isValidData(): Boolean {
        return application.resources.getStringArray(R.array.array_dir_name).size == application.resources.getStringArray(R.array.array_section_display_name).size
                && application.resources.getIntArray(R.array.array_category_logo_drawables).size == application.resources.getIntArray(R.array.array_category_colors).size
                && application.resources.getIntArray(R.array.array_category_logo_drawables).size == application.resources.getStringArray(R.array.array_dir_name).size
    }

    private fun getCategorySize(): Int {
        return application.resources.getStringArray(R.array.array_dir_name).size
    }

    fun getCategory(index: Int): Category? {
        return cacheData[index]
    }

    fun getAllCategory(): ArrayList<Category> {
        val data = ArrayList<Category>()
        cacheData.forEach { _, v ->
            data.add(v)
        }
        return data
    }

    fun getAllCategoriesName(): List<String> {
        return application.resources.getStringArray(R.array.array_dir_name).asList()
    }
}