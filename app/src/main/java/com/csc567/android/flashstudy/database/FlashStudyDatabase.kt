package com.csc567.android.flashstudy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csc567.android.flashstudy.Course
import com.csc567.android.flashstudy.FlashSet
import com.csc567.android.flashstudy.FlashCard

@Database(entities = [Course::class, FlashSet::class, FlashCard::class], version = 1)
@TypeConverters(FlashStudyTypeConverters::class)
abstract class FlashStudyDatabase:RoomDatabase() {

    abstract fun courseDao(): CourseDao

    abstract fun flashSetDao(): FlashSetDao
    abstract fun flashCardDao(): FlashCardDao
}