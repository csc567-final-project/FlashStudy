package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class FlashCardActivity : AppCompatActivity() {

    private lateinit var quizButton: Button
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
        title = "CSC 567 - Exam 1"

        val cards = getDummyData(10)

        val flashCardRecyclerView: RecyclerView = findViewById(R.id.flash_card_recycler)

        flashCardRecyclerView.adapter = FlashCardAdapter(cards)
        flashCardRecyclerView.layoutManager = LinearLayoutManager(this)
        flashCardRecyclerView.setHasFixedSize(true)

        quizButton = findViewById(R.id.quiz_button)

        quizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)

            startActivity(intent)
        }


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
                R.id.nav_class -> {
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

    private fun getDummyData(size: Int): List<FlashCard> {
        val list = ArrayList<FlashCard>()

        for (i in 0 until size) {
            val card = FlashCard("What is the Answer to this question?", "The Answer is this")
            list += card
        }

        return list
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}