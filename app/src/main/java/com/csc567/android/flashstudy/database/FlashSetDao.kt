package com.csc567.android.flashstudy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csc567.android.flashstudy.FlashSet

@Dao
interface FlashSetDao {
    @Query("select * from flashSet")
    fun getFlashSets(): LiveData<List<FlashSet>>

    @Insert
    fun insertFlashSet(flashSet: FlashSet)
}