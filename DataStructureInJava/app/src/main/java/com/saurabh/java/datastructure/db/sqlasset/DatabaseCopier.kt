package com.saurabh.java.datastructure.db.sqlasset

import android.content.Context
import timber.log.Timber
import java.io.FileOutputStream
import java.io.IOException

object DatabaseCopier {
    private const val bufferLength : Int = 8192
     fun copyAttachedDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)

        // If the database already exists, return
        if (dbPath.exists()) {
            return
        }

        // Make sure we have a path to the file
        dbPath.parentFile.mkdirs()

        // Try to copy database file
        try {
            val inputStream = context.assets.open("databases/$databaseName")
            val output = FileOutputStream(dbPath)

            val buffer = ByteArray(bufferLength)
            var length: Int = inputStream.read(buffer, 0, bufferLength)


            while (length > 0) {
                output.write(buffer, 0, length)
                length = inputStream.read(buffer, 0, bufferLength)
            }

            output.flush()
            output.close()
            inputStream.close()
        } catch (e: IOException) {
            Timber.i("Failed to open file", e)
            e.printStackTrace()
        }

    }
}