package com.csc567.android.flashstudy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csc567.android.flashstudy.FlashCard
import java.util.*

@Dao
interface FlashCardDao {
    @Query("select * from flashcard where flashSetId = :flashSetId")
    fun getFlashCards(flashSetId: UUID) : LiveData<List<FlashCard>>

    @Insert
    fun insertFlashCard(flashCard: FlashCard)
}