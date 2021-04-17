package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import java.util.*

class QuizActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }
    private lateinit var flashCardListLiveData: LiveData<List<FlashCard>>
    private lateinit var flashCards: List<FlashCard>
    private var numFlashCards: Int = 0
    private var currentIndex = 0
    private lateinit var questionTextView: TextView
    private lateinit var answerInputView: EditText
    private lateinit var submitButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        title = "Quiz"

        questionTextView = findViewById(R.id.question_text)

        answerInputView = findViewById(R.id.answer_input)

        submitButton = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            checkAnswer(answerInputView.text.toString())
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            moveToNext()
            updateQuestion()
        }

        previousButton = findViewById(R.id.previous_button)
        previousButton.setOnClickListener {
            moveToPrevious()
            updateQuestion()
        }

        val flashSetId: UUID = intent.getSerializableExtra("flashSetId") as UUID
        flashCardListLiveData = quizViewModel.flashCardRepository.getFlashCards(flashSetId)

        flashCardListLiveData.observe(
            this,
            androidx.lifecycle.Observer { flashCardList ->
                flashCardList?.let {
                    flashCards = flashCardList
                    numFlashCards = flashCards.size
                    updateQuestion()
                }
            }
        )



        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_course -> {
                    var intent = Intent(this, CourseActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_card -> {
                    var intent = Intent(this, FlashCardSetActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateQuestion() {
        questionTextView.text = flashCards[currentIndex].question
        answerInputView.setText("")
    }

    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = flashCards[currentIndex].answer

        val messageResId = when {
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    fun moveToNext() {
        if (currentIndex != numFlashCards - 1) {
            currentIndex += 1
        }
    }

    fun moveToPrevious() {
        if (currentIndex != 0) {
            currentIndex -= 1
        }
    }

}