package com.resende.todolist.domain.repository

import com.resende.todolist.data.model.Todo
import kotlinx.coroutines.flow.Flow


interface TodoRepository{
    suspend fun saveTodo(todo: Todo): Long
    suspend fun getTodo(): Flow<MutableList<Todo>>
    suspend fun deleteTodo(id: Long)
    suspend fun updateTodo(todo: Todo)
}