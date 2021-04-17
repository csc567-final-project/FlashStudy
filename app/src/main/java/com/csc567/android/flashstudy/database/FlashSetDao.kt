package com.csc567.android.flashstudy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csc567.android.flashstudy.FlashSet
import java.util.*

@Dao
interface FlashSetDao {
    @Query("SELECT * FROM FlashSet WHERE courseId = (:courseId)")
    fun getCourseFlashSets(courseId: UUID): LiveData<List<FlashSet>>

    @Query(
        "SELECT fs.id, c.courseCode || ' - ' || fs.flashSetName as flashSetName, fs.courseId FROM FlashSet fs LEFT JOIN Course c ON fs.courseId = c.id")
    fun getFlashSets(): LiveData<List<FlashSet>>

    @Insert
    fun insertFlashSet(flashSet: FlashSet)
}