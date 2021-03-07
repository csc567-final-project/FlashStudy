package com.csc567.android.flashstudy
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class FlashSetAdapter(private val listener: OnFlashSetItemClickListener, private val flashSetList: List<FlashSetItem>):
        RecyclerView.Adapter<FlashSetAdapter.FlashSetViewHolder>() {
    inner class FlashSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private lateinit var  goToFlashCards: View
        init {
            itemView.setOnClickListener(this)
        }
        val flashSetNameView: TextView = itemView.findViewById(R.id.flashSetName)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnFlashSetItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.flash_card_set_item, parent, false)
        return FlashSetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashSetViewHolder, position: Int) {
        val currentItem = flashSetList[position]
        holder.flashSetNameView.text = currentItem.textResource
    }

    override fun getItemCount() = flashSetList.size

}
