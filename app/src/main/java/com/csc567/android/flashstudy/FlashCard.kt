package com.csc567.android.flashstudy

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FlashCard(@PrimaryKey val id: UUID = UUID.randomUUID(), val question: String, val answer: String, val flashSetId: UUID)
