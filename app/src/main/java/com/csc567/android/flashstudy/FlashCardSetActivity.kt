package com.csc567.android.flashstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlashCardSetActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set)

        val flashsetlist = getDummyData(4)

        val flashSetRecyclerView: RecyclerView = findViewById(R.id.recycler_view)

        flashSetRecyclerView.adapter = FlashSetAdapter(this, flashsetlist)
        flashSetRecyclerView.layoutManager = LinearLayoutManager(this)
        flashSetRecyclerView.setHasFixedSize(true)

    }
    private fun getDummyData(size: Int): List<FlashSetItem> {
        val list = ArrayList<FlashSetItem>()

        for (i in 0 until size) {
            val flash_set= FlashSetItem("CSC 130 test " + (i + 1))
            list += flash_set
        }

        return list
    }

    fun onItemClick(position: Int) {
        var intent = Intent(this, FlashCardSetActivity::class.java)

        startActivity(intent)
    }


}


/*
class CourseActivity : AppCompatActivity(), CourseAdapter.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        title = "My Courses"

        val courses = getDummyData(5)

        val courseRecyclerView: RecyclerView = findViewById(R.id.course_recycler)

        courseRecyclerView.adapter = CourseAdapter(courses, this)
        courseRecyclerView.layoutManager = LinearLayoutManager(this)
        courseRecyclerView.setHasFixedSize(true)

    }

    private fun getDummyData(size: Int): List<Course> {
        val list = ArrayList<Course>()

        for (i in 0 until size) {
            val course = Course("CSC 100", "Intro to Computer Science")
            list += course
        }

        return list
    }

    override fun onItemClick(position: Int) {
        var intent = Intent(this, FlashCardSetActivity::class.java)

        startActivity(intent)
    }
}*/
