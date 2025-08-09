package com.ifarmer.movielist.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ifarmer.movielist.data.datasource.local.database.AppDatabase

class FakeDatabaseProvider {
    companion object {
        fun createInMemoryDatabase(): AppDatabase {
            val context = ApplicationProvider.getApplicationContext<Context>()
            return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries() // Only for tests
                .build()
        }
    }
}