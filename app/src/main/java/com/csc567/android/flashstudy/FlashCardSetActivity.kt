package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlashCardSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)

        val cards = getDummyData(10)

        val flashCardRecyclerView: RecyclerView = findViewById(R.id.flash_card_recycler)

        flashCardRecyclerView.adapter = FlashCardAdapter(cards)
        flashCardRecyclerView.layoutManager = LinearLayoutManager(this)
        flashCardRecyclerView.setHasFixedSize(true)

    }

    private fun getDummyData(size: Int): List<FlashCard> {
        val list = ArrayList<FlashCard>()

        for (i in 0 until size) {
            val card = FlashCard("What is the Answer to this question?", "The Answer is this")
            list += card
        }

        return list
    }
}