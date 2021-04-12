package com.csc567.android.flashstudy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csc567.android.flashstudy.Course

@Dao
interface CourseDao {
    @Query("select * from course")
    fun getCourses() : LiveData<List<Course>>

    @Insert
    fun insertCourse(course: Course)
}