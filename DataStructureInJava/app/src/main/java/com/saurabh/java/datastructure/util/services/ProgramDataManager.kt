package com.saurabh.java.datastructure.util.services

import android.content.Context
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.tables.Program
import com.saurabh.java.datastructure.util.LookupTable
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.io.InputStream

class ProgramDataManager (val dao: ProgramDao, private val lookupTable: LookupTable, val context: Context) {

    init {
        start()
    }

    private fun start() {
        dao.clearData()
        val directories = lookupTable.getAllCategoriesName()
        val list = ArrayList<Program>()
        for (name in directories) {
            list.addAll(getAllPrograms(context, name))
        }
        dao.insertAll(list)
    }

    private fun getAllPrograms(context: Context, categoryName: String) : ArrayList<Program> {
        val list = ArrayList<Program>()
        val assetManager = context.resources.assets
        val fileList = assetManager.list(categoryName)
        fileList?.let {
            for (programInfo in fileList.withIndex()) {
                val lastDot = programInfo.value.lastIndexOf(".")
                val title = programInfo.value.substring(2, lastDot).trim()
                val filePath = categoryName + File.separator + programInfo.value
                val program = Program(programInfo.index, title, categoryName,
                        filePath, 0, getProgramCode(context, filePath))
                list.add(program)
            }
        }
        return list
    }

    private fun getProgramCode(context: Context, filePath: String): String {
        var output = Constants.EMPTY_STRING
        try {
            val stream: InputStream = context.assets.open(filePath)
            val buffer = ByteArray(stream.available())
            stream.read(buffer)
            stream.close()
            output = String(buffer)
        } catch (e: IOException) {
            Timber.i("Exception on getProgramCode ${e.stackTrace}")
        }
        return output
    }
}