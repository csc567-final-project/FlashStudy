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
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*


class FlashSetFragment : Fragment() {

    interface Callbacks {
        fun onFlashSetSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var flashSetRecyclerView: RecyclerView
    private var adapter: FlashSetAdapter? = null
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var addFlashSetButton: FloatingActionButton

    private lateinit var flashSetListLiveData: LiveData<List<FlashSet>>

    private val flashSetViewModel:FlashSetViewModel by lazy {
        ViewModelProvider(this).get(FlashSetViewModel::class.java)
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
        val view = inflater.inflate(R.layout.fragment_flash_set, container, false)

        flashSetRecyclerView = view.findViewById(R.id.flash_set_recycler) as RecyclerView
        flashSetRecyclerView.layoutManager = LinearLayoutManager(context)



        addFlashSetButton = view.findViewById(R.id.add_flash_set_button)

        if (activity?.intent?.getSerializableExtra("courseId") != null) {
            val courseId: UUID = activity?.intent?.getSerializableExtra("courseId") as UUID

            flashSetListLiveData = flashSetViewModel.flashSetRepository.getCourseFlashSets(courseId)

            addFlashSetButton.setOnClickListener {
                var intent = Intent(activity, FlashSetCreateActivity::class.java)
                intent.putExtra("courseId", courseId)
                startActivity(intent)
            }
        } else {
            flashSetListLiveData = flashSetViewModel.flashSetRepository.getFlashSets()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flashSetListLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { flashSets ->
                flashSets?.let {
                    updateUI(flashSets)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(flashSets: List<FlashSet>) {
        adapter = FlashSetAdapter(flashSets)
        flashSetRecyclerView.adapter = adapter
    }

    private inner class FlashSetHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var flashSet: FlashSet

        private var flashSetNameView: TextView = itemView.findViewById(R.id.flash_set_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(flashSet: FlashSet) {
            this.flashSet = flashSet
            flashSetNameView.text = this.flashSet.flashSetName
        }

        override fun onClick(v: View) {
            callbacks?.onFlashSetSelected(flashSet.id)
        }

    }

    private inner class FlashSetAdapter(var flashSets: List<FlashSet>) : RecyclerView.Adapter<FlashSetHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSetHolder {
            val view = layoutInflater.inflate(R.layout.flash_card_set_item, parent, false)
            return FlashSetHolder(view)
        }

        override fun getItemCount() = flashSets.size

        override fun onBindViewHolder(holder: FlashSetHolder, position: Int) {
            val flashSet = flashSets[position]
            holder.bind(flashSet)
        }
    }

    companion object {
        fun newInstance():FlashSetFragment {
            return FlashSetFragment()
        }
    }
}