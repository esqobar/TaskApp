package com.example.taskstodo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstodo.R
import com.example.taskstodo.model.Task
import kotlinx.android.synthetic.main.list_item.view.*

class TaskListAdapter (private  val mContext: Context) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>(){

    private lateinit var mTasks: List<Task>

    fun getTasks() = mTasks

    fun setTasks(tasks: List<Task>){
        mTasks = tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
       return mTasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var currentTask = mTasks[position]
        holder.taskTextView.text =currentTask.title
        holder.taskTextView1.text =currentTask.description
        holder.taskTextView2.text =currentTask.date
    }
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var taskTextView: TextView = itemView.findViewById(R.id.titleTxt)
        var taskTextView1: TextView = itemView.findViewById(R.id.descriptionTxt)
        var taskTextView2: TextView = itemView.findViewById(R.id.dateTxt)

    }
}