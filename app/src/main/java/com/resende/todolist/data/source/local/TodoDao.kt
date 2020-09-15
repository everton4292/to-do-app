package com.resende.todolist.data.source.local

import androidx.room.*
import com.resende.todolist.data.model.Todo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun getTodoList():Flow<MutableList<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: Todo): Long

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("DELETE FROM todo_table WHERE id = :id")
    suspend fun deleteTodo(id: Long)
}