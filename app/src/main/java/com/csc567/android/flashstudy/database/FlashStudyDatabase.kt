package com.csc567.android.flashstudy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.csc567.android.flashstudy.Course
import com.csc567.android.flashstudy.FlashSet
import com.csc567.android.flashstudy.FlashCard

@Database(entities = [Course::class, FlashSet::class, FlashCard::class], version = 2)
@TypeConverters(FlashStudyTypeConverters::class)
abstract class FlashStudyDatabase:RoomDatabase() {

    abstract fun courseDao(): CourseDao

    abstract fun flashSetDao(): FlashSetDao
    abstract fun flashCardDao(): FlashCardDao
}

val migration_1_2 = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE FlashCard ADD COLUMN flashSetId TEXT NOT NULL DEFAULT ''"
        )
    }
}