package com.saurabh.java.datastructure.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.saurabh.java.datastructure.constants.Constants
import com.saurabh.java.datastructure.db.AppDatabase
import com.saurabh.java.datastructure.db.ProgramDatabase
import com.saurabh.java.datastructure.db.dao.FAQDao
import com.saurabh.java.datastructure.db.dao.ProgramDao
import com.saurabh.java.datastructure.db.sqlasset.DatabaseCopier
import com.saurabh.java.datastructure.util.PrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by saurabh.khare on 2018/06/25.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application, prefManager: PrefManager): AppDatabase {
        val name = "ds_droid.db"
        if (prefManager.getValue(Constants.KEY_FIRST_LAUNCH).isNullOrBlank()) {
            DatabaseCopier.copyAttachedDatabase(app.applicationContext, name)
        }
        return Room.databaseBuilder(app, AppDatabase::class.java, name)
                .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideFAQDao(db: AppDatabase): FAQDao {
        return db.faqdao()
    }

    @Singleton
    @Provides
    fun provideProgramDb(app: Application): ProgramDatabase {
        val name = "ds_droid_internal.db"
        return Room.databaseBuilder(app, ProgramDatabase::class.java, name)
                .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideProgramDao(db: ProgramDatabase): ProgramDao {
        return db.programDao()
    }

    @Singleton
    @Provides
    fun getSharedPreference(context: Application): PrefManager {
        return PrefManager(context)
    }
}