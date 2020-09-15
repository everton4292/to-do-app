package com.resende.todolist.di.module

import com.resende.todolist.presentation.greetings.GreetingRepositoryImpl
import com.resende.todolist.presentation.greetings.GreetingsViewModel
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { createGreetingRepository(get()) }
    single { createTodoRepository(get()) }

    viewModel { TodoViewModel(get()) }
    viewModel { GreetingsViewModel(get()) }

    factory { TodoAdapter() }
}
