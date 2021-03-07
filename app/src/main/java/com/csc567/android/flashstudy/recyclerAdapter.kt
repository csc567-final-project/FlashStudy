package com.csc567.android.flashstudy
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class FlashSetAdapter(private val listener: FlashCardSetActivity, private val flashSetList: List<FlashSetItem>):
        RecyclerView.Adapter<FlashSetAdapter.recyclerViewHolder>() {
    inner class FlashSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        val courseNameView: TextView = itemView.findViewById(R.id.flashSetName)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.flash_card_set_item, parent, false)
        return recyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {
        val currentItem = flashSetList[position]
        holder.textView1.text = currentItem.textResource
        holder.itemView.setOnClickListener(View.OnClickListener(){
             fun onClick(v: View){

            }
        });
    }

    override fun getItemCount() = flashSetList.size

    class recyclerViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        val textView1: TextView = itemView.findViewById(R.id.flashSetName)

    }

}


/*
class CourseAdapter (
        private val courseList: List<Course>,
        private val listener: OnItemClickListener
): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        val courseCodeView: TextView = itemView.findViewById(R.id.course_code_view)
        val courseNameView: TextView = itemView.findViewById(R.id.course_name_view)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item,
                parent, false)

        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentItem = courseList[position]

        holder.courseCodeView.text = currentItem.courseCode
        holder.courseNameView.text = currentItem.courseName
    }

    override fun getItemCount() = courseList.size


}
*/
