package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FlashCardSetActivity : AppCompatActivity() {

    private lateinit var goToQuizButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)

        goToQuizButton = findViewById(R.id.quiz_page_button)

        goToQuizButton.setOnClickListener {
            // Takes you to the quiz page
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}