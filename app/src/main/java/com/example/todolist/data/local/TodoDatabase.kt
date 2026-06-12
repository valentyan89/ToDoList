package com.example.todolist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.local.entity.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}