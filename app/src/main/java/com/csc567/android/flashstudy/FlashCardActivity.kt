package com.csc567.android.flashstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
class FlashCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.flash_card_fragment_container)
        if (currentFragment == null) {
            val fragment = FlashCardFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.flash_card_fragment_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.flash_card_fragment_container) as FlashCardFragment
        if(currentFragment.toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}