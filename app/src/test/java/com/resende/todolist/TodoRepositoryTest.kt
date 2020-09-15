package com.resende.todolist


import com.resende.todolist.data.model.AccessTokenResponse
import com.resende.todolist.data.model.Todo
import com.resende.todolist.data.repository.TodoRepositoryImp
import com.resende.todolist.domain.repository.TodoRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class TodoRepositoryTest {

    @Mock
    var repository: TodoRepository = Mockito.mock(TodoRepositoryImp::class.java)
    @Mock
    val todoTest1 = Todo(id = 5, title = "Comer", description = "Almoçar às 12:00")
    @Mock
    val todoTest2 = Todo(id = 7, title = "jantar", description = "Jantar às 18:00")
    @Mock
    val todoTestFail1 = Todo(id = 1, title = "", description = "Correr às 19:00")
    @Mock
    val todoList: Flow<MutableList<Todo>> = flowOf(mutableListOf<Todo>(todoTest1, todoTest2))


    @Test
    fun `Success save todo when all fields are valid`(){
        runBlocking {
            `when`(repository.saveTodo(todoTest1)).thenReturn(0)
            assertEquals(0, repository.saveTodo(todoTest1))
        }
    }

    @Test
    fun `Fail save todo when title is Null`(){
        runBlocking {
            `when`(repository.saveTodo(todoTestFail1)).thenReturn(1)
            assertEquals(1, repository.saveTodo(todoTestFail1))
        }
    }

    @Test
    fun `Success delete todo`(){
        runBlocking {
            `when`(repository.deleteTodo(todoTest2.id.toString().toLong())).thenReturn(Unit)
            assertEquals(Unit, repository.deleteTodo(todoTest2.id.toString().toLong()))
        }
    }

    @Test
    fun `Success update todo`(){
        runBlocking {
            `when`(repository.updateTodo(todoTest1)).thenReturn(Unit)
            assertEquals(Unit, repository.updateTodo(todoTest2))
        }
    }

    @Test
    fun `Success get todo`(){
        runBlocking {
            `when`(repository.getTodo()).thenReturn(todoList)
            assertEquals(todoList, repository.getTodo())
        }
    }
}




