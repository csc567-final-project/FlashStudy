package com.csc567.android.flashstudy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.csc567.android.flashstudy.database.FlashStudyDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "flash-study-database"

class FlashCardRepository  private constructor(context: Context) {

    private val database : FlashStudyDatabase = Room.databaseBuilder(
        context.applicationContext,
        FlashStudyDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val flashCardDao = database.flashCardDao()

    fun getFlashCards(flashSetId: UUID): LiveData<List<FlashCard>> = flashCardDao.getFlashCards(flashSetId)
    fun insertFlashCard(flashCard: FlashCard) = flashCardDao.insertFlashCard(flashCard)

    companion object {
        private var INSTANCE:FlashCardRepository?=null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FlashCardRepository(context)
            }
        }

        fun get(): FlashCardRepository {
            return INSTANCE?:throw IllegalStateException("CourseRepository must be initialized")
        }
    }

}