package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

class CourseActivity : AppCompatActivity(), CourseFragment.Callbacks {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var courseRecyclerView: RecyclerView
    private lateinit var addCourseButton: FloatingActionButton
    private var adapter: CourseAdapter? = null

    private val courseViewModel:CourseViewModel by lazy {
        ViewModelProvider(this).get(CourseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = CourseFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

        /*
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
         */
    }

    override fun onCourseSelected(crimeId: UUID) {
        var intent = Intent(this, FlashCardSetActivity::class.java)

        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}