package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class FlashCardCreateActivity : AppCompatActivity() {

    private lateinit var questionInput: EditText
    private lateinit var answerInput: EditText
    private lateinit var saveFlashCardButton: Button
    private val flashCardRepository = FlashCardRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_flash_card_create)

        questionInput = findViewById(R.id.flash_question_input)
        answerInput = findViewById(R.id.flash_answer_input)
        saveFlashCardButton = findViewById(R.id.create_flash_card_window_button)

        saveFlashCardButton.setOnClickListener {
            var currentActivity = this
            var flashSetId: UUID = intent.getSerializableExtra("flashSetId") as UUID
            var flashCard = FlashCard(flashSetId = flashSetId, question = questionInput.text.toString(), answer = answerInput.text.toString())
            val thread = Thread {
                flashCardRepository.insertFlashCard(flashCard)
                currentActivity.finishActivity(0)
                var intent = Intent(currentActivity, FlashCardActivity::class.java)
                intent.putExtra("flashSetId", flashSetId)
                startActivity(intent)
            }
            thread.start()
        }

    }
}