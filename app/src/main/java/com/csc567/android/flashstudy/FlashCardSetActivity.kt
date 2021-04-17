package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*

class FlashCardSetActivity : AppCompatActivity(), FlashSetFragment.Callbacks{

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var courseId: UUID
    private val flashSetViewModel:FlashSetViewModel by lazy {
        ViewModelProvider(this).get(FlashSetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.flash_set_fragment_container)
        if (currentFragment == null) {
            val fragment = FlashSetFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.flash_set_fragment_container, fragment)
                    .commit()
        }
    }

    override fun onFlashSetSelected(flashSetId: UUID) {
        var intent = Intent(this, FlashCardSetActivity::class.java)
        intent.putExtra("flashSetId", flashSetId)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
