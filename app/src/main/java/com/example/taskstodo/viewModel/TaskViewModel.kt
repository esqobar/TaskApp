package com.example.taskstodo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskstodo.model.Task
import com.example.taskstodo.repository.TaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val taskRepository:TaskRepository
    private val allTasks: LiveData<List<Task>>

    init {
        taskRepository = TaskRepository(application)
        allTasks = taskRepository.getAllTasks()
    }

    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }

    fun  deleteTask(task: Task){
        taskRepository.deleteTask(task)
    }

    fun deleteAllTasks(){
        taskRepository.deleteAllTasks()
    }

    fun getAllTasks() = allTasks

    fun getTaskByTitle(title:String):Task? {
        return taskRepository.getTaskByTitle(title)
    }
}