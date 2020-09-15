package com.resende.todolist.data.source.local

import androidx.room.*
import com.resende.todolist.data.model.Todo

@Database(entities = [Todo::class], version = 2, exportSchema = true)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

}