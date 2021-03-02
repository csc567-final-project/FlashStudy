package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var goToCourseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToCourseButton = findViewById(R.id.course_page_button)

        goToCourseButton.setOnClickListener {
            // Takes you to the Course page with the list of Flash Card Sets
            val intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }
    }


}