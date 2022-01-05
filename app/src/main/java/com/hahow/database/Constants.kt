package com.hahow.database

import androidx.room.migration.Migration

internal object Constants {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "hahow.db"
    val MIGRATIONS : Array<Migration> = arrayOf()
}