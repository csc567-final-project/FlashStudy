package com.csc567.android.flashstudy.database

import androidx.room.TypeConverter
import java.util.*

class  FlashStudyTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
}