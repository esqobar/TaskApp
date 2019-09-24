package com.example.taskstodo.interFace

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskstodo.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun  deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY title ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE title = :title")
    fun getTaskByTitle(title:String):Task


}