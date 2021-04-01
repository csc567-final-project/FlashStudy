package com.csc567.android.flashstudy

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FlashSet (@PrimaryKey val id: UUID = UUID.randomUUID(), val textResource: String)