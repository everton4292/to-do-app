package com.resende.todolist


import com.resende.todolist.data.model.Todo
import com.resende.todolist.data.repository.TodoRepositoryImp
import com.resende.todolist.domain.repository.TodoRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class TodoRepositoryTest {

    var repository: TodoRepository = Mockito.mock(TodoRepositoryImp::class.java)
    val todo1 = Todo(id = 5, title = "Comer", description = "Almoçar às 12:00")
    val todo2 = Todo(id = 7, title = "jantar", description = "Jantar às 18:00")

    @Test
    fun `Success save todo`(){
        runBlocking {
            `when`(repository.saveTodo(todo1)).thenReturn(0)
            assertEquals(0, repository.saveTodo(todo1))
        }
    }

    //TODO completar os testes
}




