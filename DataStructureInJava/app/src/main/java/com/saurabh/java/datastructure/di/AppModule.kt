package com.saurabh.java.datastructure.di

import android.app.Application
import androidx.room.Room
import com.saurabh.java.datastructure.db.AppDatabase
import com.saurabh.java.datastructure.db.dao.FAQDao
import com.saurabh.java.datastructure.db.sqlasset.DatabaseCopier
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
    fun provideDb(app: Application): AppDatabase {
        val name = "ds_droid.db"
        DatabaseCopier.copyAttachedDatabase(app, name)
        return Room.databaseBuilder(app, AppDatabase::class.java, name)
                .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideFAQDao(db: AppDatabase): FAQDao {
        return db.faqdao()
    }
}