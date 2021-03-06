package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FlashCardSetActivity : AppCompatActivity() {

    private lateinit var goToCardsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)

        goToCardsButton = findViewById(R.id.flash_card_page_button)

        goToCardsButton.setOnClickListener {
            var intent = Intent(this, FlashCardActivity::class.java)

            startActivity(intent)
        }
    }
}