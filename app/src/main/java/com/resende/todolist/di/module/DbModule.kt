package com.resende.todolist.di.module


import androidx.room.Room
import com.resende.todolist.data.source.local.TodoDatabase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            TodoDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<TodoDatabase>().todoDao() }
}