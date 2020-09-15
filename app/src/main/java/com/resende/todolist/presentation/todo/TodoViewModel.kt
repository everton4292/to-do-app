package com.resende.todolist.presentation.todo

import androidx.lifecycle.MutableLiveData
import com.resende.todolist.data.model.Todo
import com.resende.todolist.domain.repository.TodoRepository
import com.resende.todolist.presentation.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception


class TodoViewModel(private val todoRepository: TodoRepository) : BaseViewModel() {

    val todoList: MutableLiveData<MutableList<Todo>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getTodos() {
            try {
                scope.launch {
                    todoRepository.getTodo().collect {
                        todoList.value = it
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }


    fun saveTodo(todo: Todo) {
        scope.launch {
            try {
                todoRepository.saveTodo(todo)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun updateTodo(todo: Todo) {
        scope.launch {
            try {
                todoRepository.updateTodo(todo)
            } catch (e: Exception){
                errorMessage.value = e.message
            }
        }
    }

    fun deleteTodo(id: Long) {
        try {
            scope.launch {
                todoRepository.deleteTodo(id)
            }
        } catch (e: Exception) {
            errorMessage.value = e.message
        }
    }
}