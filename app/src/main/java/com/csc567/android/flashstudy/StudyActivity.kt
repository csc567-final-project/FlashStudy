package com.csc567.android.flashstudy

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import java.util.*

class StudyActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private val studyViewModel: StudyViewModel by lazy {
        ViewModelProvider(this).get(StudyViewModel::class.java)
    }
    private lateinit var flashCardListLiveData: LiveData<List<FlashCard>>
    private lateinit var flashCards: List<FlashCard>
    private var numFlashCards: Int = 0
    private var currentIndex = 0
    private lateinit var flashCardQuestion: TextView
    private lateinit var flashCardAnswer: TextView
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var flipButton: Button

    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        title = "Study"

        nextButton = findViewById(R.id.study_next_button)
        nextButton.setOnClickListener {
            moveToNext()
            updateQuestion()
        }

        previousButton = findViewById(R.id.study_previous_button)
        previousButton.setOnClickListener {
            moveToPrevious()
            updateQuestion()
        }

        val scale = applicationContext.resources.displayMetrics.density

        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet


        flashCardQuestion = findViewById(R.id.flash_card_question)
        flashCardQuestion.cameraDistance = 8000 * scale
        flashCardQuestion.setOnClickListener {
            if (isFront) {
                frontAnim.setTarget(flashCardQuestion)
                backAnim.setTarget(flashCardAnswer)
                frontAnim.start()
                backAnim.start()
                isFront = false
            } else {
                frontAnim.setTarget(flashCardAnswer)
                backAnim.setTarget(flashCardQuestion)
                backAnim.start()
                frontAnim.start()
                isFront = true
            }
        }

        flashCardAnswer = findViewById(R.id.flash_card_answer)
        flashCardAnswer.cameraDistance = 8000 * scale

        val flashSetId: UUID = intent.getSerializableExtra("flashSetId") as UUID
        flashCardListLiveData = studyViewModel.flashCardRepository.getFlashCards(flashSetId)

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
        flashCardQuestion.text = flashCards[currentIndex].question
        flashCardAnswer.text = flashCards[currentIndex].answer
        if (!isFront) {
            frontAnim.setTarget(flashCardAnswer)
            backAnim.setTarget(flashCardQuestion)
            backAnim.start()
            frontAnim.start()
            isFront = true
        }
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