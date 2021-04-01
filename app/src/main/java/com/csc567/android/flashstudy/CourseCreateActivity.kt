package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class CourseCreateActivity : AppCompatActivity() {

    private lateinit var courseCodeInput: EditText
    private lateinit var courseNameInput: EditText
    private lateinit var saveCourseButton: Button
    private val courseRepository = CourseRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_course_create)

        courseCodeInput = findViewById(R.id.course_code_input)
        courseNameInput = findViewById(R.id.course_name_input)
        saveCourseButton = findViewById(R.id.create_course_window_button)

        saveCourseButton.setOnClickListener {
            var course = Course(courseCode = courseCodeInput.text.toString(), courseName = courseNameInput.text.toString())
            var insertedId: Long = courseRepository.insertCourse(course)
            finishActivity(0)
        }

    }
}