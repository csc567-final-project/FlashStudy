package com.csc567.android.flashstudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.security.identity.SessionTranscriptMismatchException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*


class CourseFragment : Fragment() {

    interface Callbacks {
        fun onCourseSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var courseRecyclerView: RecyclerView
    private var adapter: CourseAdapter? = null
    private lateinit var addCourseButton: FloatingActionButton
    lateinit var toggle: ActionBarDrawerToggle

    private val courseViewModel:CourseViewModel by lazy {
        ViewModelProvider(this).get(CourseViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)

        courseRecyclerView = view.findViewById(R.id.course_recycler) as RecyclerView
        courseRecyclerView.layoutManager = LinearLayoutManager(context)

        addCourseButton = view.findViewById(R.id.add_course_button)
        addCourseButton.setOnClickListener {
            var intent = Intent(activity, CourseCreateActivity::class.java)
            startActivity(intent)
        }

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = view.findViewById<NavigationView>(R.id.nav_view)

        toggle = ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    var intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_class -> {
                    var intent = Intent(activity, CourseActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_card -> {
                    var intent = Intent(activity, FlashCardSetActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseViewModel.courseListLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { crimes ->
                crimes?.let {
                    updateUI(crimes)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(courses: List<Course>) {
        adapter = CourseAdapter(courses)
        courseRecyclerView.adapter = adapter
    }

    private inner class CourseHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var course: Course

        private var courseCodeView: TextView = itemView.findViewById(R.id.course_code_view)
        private var courseNameView: TextView = itemView.findViewById(R.id.course_name_view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(course: Course) {
            this.course = course
            courseCodeView.text = this.course.courseCode
            courseNameView.text = this.course.courseName
        }

        override fun onClick(v: View) {
            callbacks?.onCourseSelected(course.id)
        }

    }

    private inner class CourseAdapter(var courses: List<Course>) : RecyclerView.Adapter<CourseHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
            val view = layoutInflater.inflate(R.layout.course_item, parent, false)
            return CourseHolder(view)
        }

        override fun getItemCount() = courses.size

        override fun onBindViewHolder(holder: CourseHolder, position: Int) {
            val course = courses[position]
            holder.bind(course)
        }
    }

    companion object {
        fun newInstance():CourseFragment {
            return CourseFragment()
        }
    }
}