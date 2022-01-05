package com.hahow.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hahow.database.entity.DatabaseCourse
import com.hahow.database.table.CourseTable

@androidx.room.Database(
    entities = [
        DatabaseCourse::class
    ],
    version = Constants.DATABASE_VERSION
)
abstract class Database: RoomDatabase() {
    companion object {
        private var instance: Database? = null
        fun getInstance(context: Context): Database {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context, Database::class.java, Constants.DATABASE_NAME)
                            .addMigrations(*Constants.MIGRATIONS)
                            .build()
                }
            }
            return instance!!
        }
    }

    abstract fun courseTable() : CourseTable
}