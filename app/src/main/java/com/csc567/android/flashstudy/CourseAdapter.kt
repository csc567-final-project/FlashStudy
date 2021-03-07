package com.csc567.android.flashstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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

