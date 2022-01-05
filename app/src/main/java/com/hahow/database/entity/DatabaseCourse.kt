package com.hahow.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = DatabaseCourse.Properties.TABLE_NAME,
    primaryKeys = [DatabaseCourse.Properties.NAME]
)
data class DatabaseCourse(
    @ColumnInfo(name = Properties.NAME) val name: String,
    @ColumnInfo(name = Properties.COVER_URL) val coverUrl: String,
    @ColumnInfo(name = Properties.STATUS) val status: String,
    @ColumnInfo(name = Properties.GOAL_PERCENTAGE) val goalPercentage: Float,
    @ColumnInfo(name = Properties.STUDENT) val student: Int,
    @ColumnInfo(name = Properties.PROPOSAL_DUE_TIME) val proposalDueTime: String,
) {
    object Properties {
        const val TABLE_NAME = "TABLE_NAME"
        const val NAME = "NAME"
        const val COVER_URL = "COVER_URL"
        const val STATUS = "STATUS"
        const val GOAL_PERCENTAGE = "GOAL_PERCENTAGE"
        const val STUDENT = "STUDENT"
        const val PROPOSAL_DUE_TIME = "PROPOSAL_DUE_TIME"
    }
}
