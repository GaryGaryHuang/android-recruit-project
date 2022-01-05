package com.hahow.database.table

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hahow.database.entity.DatabaseCourse
import com.hahow.database.entity.DatabaseCourse.Properties.TABLE_NAME

@Dao
interface CourseTable {
    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getCourse() : List<DatabaseCourse>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourse(courseList: List<DatabaseCourse>)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteCourse()
}