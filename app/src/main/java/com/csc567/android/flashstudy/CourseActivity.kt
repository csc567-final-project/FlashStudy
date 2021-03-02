package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CourseActivity : AppCompatActivity() {

    private lateinit var goToFlashCardSetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        goToFlashCardSetButton = findViewById(R.id.flash_card_set_page_button)

        goToFlashCardSetButton.setOnClickListener {
            // Takes you to the flash card set page with the list of Flash Cards
            val intent = Intent(this, FlashCardSetActivity::class.java)
            startActivity(intent)
        }
    }
}