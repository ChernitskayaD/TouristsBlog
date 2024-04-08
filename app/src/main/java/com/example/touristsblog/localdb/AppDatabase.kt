package com.example.touristsblog.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyPostsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    //abstract val weatherDatabaseDao: WeatherDatabaseDao
    //abstract val cityDatabaseDao: CityDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "DB_NAME"
                    )
                        //.createFromAsset("cities.db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

@Entity(tableName = "my_posts")
data class MyPostsEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val isOpen: Boolean,
)