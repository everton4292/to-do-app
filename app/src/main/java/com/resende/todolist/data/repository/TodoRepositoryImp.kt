package com.resende.todolist.data.repository

import com.resende.todolist.data.model.Todo
import com.resende.todolist.data.source.local.TodoDao
import com.resende.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImp(private val todoDao: TodoDao) : TodoRepository {

    override suspend fun saveTodo(todo: Todo): Long {
        return todoDao.saveTodo(todo)
    }

    override suspend fun getTodo(): Flow<MutableList<Todo>> {
        return todoDao.getTodoList()
    }

    override suspend fun deleteTodo(id: Long) {
        return todoDao.deleteTodo(id)
    }

    override suspend fun updateTodo(todo: Todo) {
        return todoDao.updateTodo(todo)
    }
}