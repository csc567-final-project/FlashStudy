package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class FlashSetCreateActivity : AppCompatActivity() {

    private lateinit var flashSetNameInput: EditText
    private lateinit var saveFlashSetButton: Button
    private val flashSetRepository = FlashSetRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_flash_set_create)

        flashSetNameInput = findViewById(R.id.flash_set_name_input)
        saveFlashSetButton = findViewById(R.id.create_flash_set_window_button)

        saveFlashSetButton.setOnClickListener {
            var currentActivity = this
            var courseId: UUID = intent.getSerializableExtra("courseId") as UUID
            var flashSet = FlashSet(flashSetName = flashSetNameInput.text.toString(), courseId = courseId)
            val thread = Thread {
                flashSetRepository.insertFlashSet(flashSet)
                currentActivity.finishActivity(0)
                var intent = Intent(currentActivity, FlashCardSetActivity::class.java)
                intent.putExtra("courseId", courseId)
                startActivity(intent)
            }
            thread.start()
        }

    }
}
