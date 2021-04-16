package com.csc567.android.flashstudy

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = arrayOf(
        ForeignKey(entity = FlashSet::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("flashSetId"),
                onDelete = CASCADE)
))
data class FlashCard(@PrimaryKey val id: UUID = UUID.randomUUID(), val flashSetId: UUID, val question: String, val answer: String)
