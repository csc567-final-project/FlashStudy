package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlashCardSetActivity : AppCompatActivity(), FlashSetAdapter.OnItemClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)
        title = "Flash Card Sets"

        val flashsetlist = getDummyData(20)

        val flashSetRecyclerView: RecyclerView = findViewById(R.id.flash_card_set_recycler)

        flashSetRecyclerView.adapter = FlashSetAdapter(this, flashsetlist)
        flashSetRecyclerView.layoutManager = LinearLayoutManager(this)
        flashSetRecyclerView.setHasFixedSize(true)

    }
    private fun getDummyData(size: Int): List<FlashSet> {
        val list = ArrayList<FlashSet>()

        for (i in 0 until size) {
            val flash_set= FlashSet("CSC 130 test " + (i + 1))
            list += flash_set
        }

        return list
    }

    override fun onItemClick(position: Int) {
        var intent = Intent(this, FlashCardActivity::class.java)

        startActivity(intent)
    }


}
