package com.csc567.android.flashstudy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.csc567.android.flashstudy.database.FlashStudyDatabase
import java.lang.IllegalStateException

private const val DATABASE_NAME = "flash-study-database"

class CourseRepository  private constructor(context: Context) {

    private val database : FlashStudyDatabase = Room.databaseBuilder(
            context.applicationContext,
            FlashStudyDatabase::class.java,
            DATABASE_NAME
    ).build()

    private val courseDao = database.courseDao()

    fun getCourses(): LiveData<List<Course>> = courseDao.getCourses()
    fun insertCourse(course: Course) = courseDao.insertCourse(course)

    companion object {
        private var INSTANCE:CourseRepository?=null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CourseRepository(context)
            }
        }

        fun get(): CourseRepository {
            return INSTANCE?:throw IllegalStateException("CourseRepository must be initialized")
        }
    }

}