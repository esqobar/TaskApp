package com.example.taskstodo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.taskstodo.interFace.TaskDao
import com.example.taskstodo.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskRoomDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        var database:TaskRoomDatabase? = null

        fun getInstance(context: Context): TaskRoomDatabase? {
            if (database == null) {
                synchronized(TaskRoomDatabase::class.java){
                    if (database == null) {
                        database = databaseBuilder(context.applicationContext, TaskRoomDatabase::class.java, "task_table").build()
                    }
                }
            }
            return database
        }
    }
}