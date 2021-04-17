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

class FlashCardActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    lateinit var flashSetId: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        flashSetId = intent.getSerializableExtra("flashSetId") as UUID

        val currentFragment = supportFragmentManager.findFragmentById(R.id.flash_card_fragment_container)
        if (currentFragment == null) {
            val fragment = FlashCardFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.flash_card_fragment_container, fragment)
                .commit()
        }

        /*

         */
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as CourseFragment
        if(currentFragment.toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}