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
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*


class FlashCardFragment : Fragment() {

    interface Callbacks {
        fun onCourseSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var flashCardRecyclerView: RecyclerView
    private var adapter: FlashCardAdapter? = null
    private lateinit var addFlashCardButton: FloatingActionButton
    private lateinit var flashCardListLiveData: LiveData<List<FlashCard>>

    private val flashCardViewModel:FlashCardViewModel by lazy {
        ViewModelProvider(this).get(FlashCardViewModel::class.java)
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

        flashCardRecyclerView = view.findViewById(R.id.flash_card_recycler) as RecyclerView
        flashCardRecyclerView.layoutManager = LinearLayoutManager(context)

        val flashSetId: UUID = activity?.intent?.getSerializableExtra("flashSetId") as UUID

        flashCardListLiveData = flashCardViewModel.flashCardRepository.getFlashCards(flashSetId)

        addFlashCardButton = view.findViewById(R.id.add_flash_card_button)
        addFlashCardButton.setOnClickListener {
            var intent = Intent(activity, FlashCardCreateActivity::class.java)
            val flashSetId: UUID = activity?.intent?.getSerializableExtra("flashSetId") as UUID
            intent.putExtra("flashSetId", flashSetId)
            startActivity(intent)
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flashCardListLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { flashCards ->
                flashCards?.let {
                    updateUI(flashCards)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(flashCards: List<FlashCard>) {
        adapter = FlashCardAdapter(flashCards)
        flashCardRecyclerView.adapter = adapter
    }

    private inner class FlashCardHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var flashCard: FlashCard

        private var flashCardQuestionView: TextView = itemView.findViewById(R.id.flash_card_question_view)

        private var flashCardAnswerView: TextView = itemView.findViewById(R.id.flash_card_answer_view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(flashCard: FlashCard) {
            this.flashCard = flashCard
            flashCardQuestionView.text = this.flashCard.question
            flashCardAnswerView.text = this.flashCard.answer
        }

        override fun onClick(v: View) {
            callbacks?.onCourseSelected(flashCard.id)
        }

    }

    private inner class FlashCardAdapter(var flashCards: List<FlashCard>) : RecyclerView.Adapter<FlashCardHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashCardHolder {
            val view = layoutInflater.inflate(R.layout.flash_card_item, parent, false)
            return FlashCardHolder(view)
        }

        override fun getItemCount() = flashCards.size

        override fun onBindViewHolder(holder: FlashCardHolder, position: Int) {
            val flashCard = flashCards[position]
            holder.bind(flashCard)
        }
    }

    companion object {
        fun newInstance():FlashCardFragment {
            return FlashCardFragment()
        }
    }
}