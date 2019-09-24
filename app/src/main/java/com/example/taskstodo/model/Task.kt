package com.example.taskstodo.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "task_table")

data class Task (@PrimaryKey @NonNull val title: String, @NonNull val description: String, @NonNull val date: String){
}