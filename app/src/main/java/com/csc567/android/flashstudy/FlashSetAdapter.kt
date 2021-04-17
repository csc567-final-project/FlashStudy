package com.csc567.android.flashstudy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FlashSetAdapter(private val listener: OnItemClickListener, private val flashSetList: List<FlashSet>):
        RecyclerView.Adapter<FlashSetAdapter.FlashSetViewHolder>() {
    inner class FlashSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private lateinit var  goToFlashCards: View
        init {
            itemView.setOnClickListener(this)
        }
        val flashSetNameView: TextView = itemView.findViewById(R.id.flash_set_name)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(flashSetList[position])
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(flashSet: FlashSet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.flash_card_set_item, parent, false)
        return FlashSetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashSetViewHolder, position: Int) {
        val currentItem = flashSetList[position]
        holder.flashSetNameView.text = currentItem.flashSetName
    }

    override fun getItemCount() = flashSetList.size

}
