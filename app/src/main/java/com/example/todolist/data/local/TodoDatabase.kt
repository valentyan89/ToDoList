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

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo.db"
                )
                    .fallbackToDestructiveMigration()
                    // .addMigrations(...)
                    // .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}