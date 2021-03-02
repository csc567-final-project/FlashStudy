package com.csc567.android.flashstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlashCardAdapter (private val flashCardList: List<FlashCard>): RecyclerView.Adapter<FlashCardAdapter.FlashCardViewHolder>() {
    class FlashCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionView: TextView = itemView.findViewById(R.id.flash_card_question_view)
        val answerView: TextView = itemView.findViewById(R.id.flash_card_answer_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.flash_card_item,
            parent, false)

        return FlashCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashCardViewHolder, position: Int) {
        val currentItem = flashCardList[position]

        holder.questionView.text = currentItem.question
        holder.answerView.text = currentItem.answer
    }

    override fun getItemCount() = flashCardList.size


}