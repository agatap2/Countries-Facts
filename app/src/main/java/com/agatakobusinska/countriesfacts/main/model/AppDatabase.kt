package com.agatakobusinska.countriesfacts.main.model


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.local.CountryDao

@Database(entities = [Country::class], version = 9)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "countries")
                .fallbackToDestructiveMigration().build()
    }

}