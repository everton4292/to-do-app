package com.resende.todolist.presentation.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.resende.todolist.R
import com.resende.todolist.data.model.Todo
import com.resende.todolist.presentation.todo.fragments.AddTodoFragment
import com.resende.todolist.presentation.todo.fragments.DeleteTodoFragment
import com.resende.todolist.presentation.todo.fragments.EditTodoFragment
import kotlinx.android.synthetic.main.activity_todo.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class TodoActivity : AppCompatActivity() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        supportActionBar?.title = getString(R.string.todo_title)
        loadRecyclerView()

        viewModel.getTodos()
        setupObservers()

        todoAdapter.setOnClickTodoListener {
            val bundleEditTodo: Bundle = bundleOf(
                Pair("edit_todo_id", it.id),
                Pair("edit_todo_name", it.title),
                Pair("edit_todo_description", it.description)
            )
            val editTodoFragment = EditTodoFragment()
            editTodoFragment.arguments = bundleEditTodo
            editTodoFragment.show(supportFragmentManager, "editTodoFragment")
        }

        removeButton.setOnClickListener {
            val deleteItemFragment = DeleteTodoFragment()
            deleteItemFragment.show(supportFragmentManager, "deleteTodoFragment")
        }

        plusAccButton.setOnClickListener {
            val addTodoFragment = AddTodoFragment()
            addTodoFragment.show(supportFragmentManager, "AddTodoFragment")
        }
    }

    
    private fun setupObservers() {
        viewModel.todoList.observe(this, Observer {
            updateTodoList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(
                this, "Erro ao atualizar valores",
                Toast.LENGTH_LONG
            ).show()
        })

    }

    private fun loadRecyclerView() {
        val linearLayoutTodo = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewTodo.apply {
            adapter = todoAdapter
            setHasFixedSize(true)
            this.layoutManager = linearLayoutTodo
        }
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }
}