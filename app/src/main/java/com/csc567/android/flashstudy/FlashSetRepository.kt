package com.csc567.android.flashstudy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.csc567.android.flashstudy.database.FlashStudyDatabase
import com.csc567.android.flashstudy.database.migration_2_3
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "flash-study-database"

class FlashSetRepository  private constructor(context: Context) {

    private val database : FlashStudyDatabase = Room.databaseBuilder(
        context.applicationContext,
        FlashStudyDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val flashSetDao = database.flashSetDao()

    fun getCourseFlashSets(courseId: UUID) = flashSetDao.getCourseFlashSets(courseId)
    fun getFlashSets(): LiveData<List<FlashSet>> = flashSetDao.getFlashSets()
    fun insertFlashSet(flashSet: FlashSet) = flashSetDao.insertFlashSet(flashSet)

    companion object {
        private var INSTANCE:FlashSetRepository?=null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FlashSetRepository(context)
            }
        }

        fun get(): FlashSetRepository {
            return INSTANCE?:throw IllegalStateException("FlashSetRepository must be initialized")
        }
    }

}