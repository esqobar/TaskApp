package com.example.taskstodo.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.taskstodo.db.TaskRoomDatabase
import com.example.taskstodo.interFace.TaskDao
import com.example.taskstodo.model.Task

class TaskRepository(application: Application) {
    private val taskDao:TaskDao?
    private val allTasks: LiveData<List<Task>>?

    init {
        val db=TaskRoomDatabase.getInstance(application)
        taskDao =db?.taskDao()
        allTasks = taskDao?.getAllTasks()
    }

    fun insertTask(task: Task){
        InsertAsyncTask(taskDao!!).execute(task)
    }

    fun deleteTask(task: Task){
        DeleteAsyncTask(taskDao!!).execute(task)
    }

    fun deleteAllTasks(){
        DeleteAllAsyncTask(taskDao!!).execute()
    }

    fun getAllTasks(): LiveData<List<Task>>{
        return  allTasks!!
    }

    fun getTaskByTitle(title:String):Task? {
        val allTaskList = allTasks?.value?.toList()

        allTaskList?.iterator()?.forEach {
            if(it.title == title ){
                return it
            }
        }
        return null
    }

    private class InsertAsyncTask(private val dao: TaskDao) : AsyncTask<Task, Void, Void>(){
        override fun doInBackground(vararg p0: Task): Void? {
            dao.insertTask(p0[0])
            return null
        }

    }

    private class DeleteAsyncTask(private val dao: TaskDao) : AsyncTask<Task, Void, Void>(){
        override fun doInBackground(vararg p0: Task): Void? {
            dao.deleteTask(p0[0])
            return null
        }

    }

    private class DeleteAllAsyncTask(private val dao: TaskDao) : AsyncTask<Task, Void, Void>(){
        override fun doInBackground(vararg p0: Task): Void? {
            dao.deleteAllTasks()
            return null
        }

    }
}