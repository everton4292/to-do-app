package com.resende.todolist.presentation.todo.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.resende.todolist.R
import androidx.lifecycle.Observer;
import com.resende.todolist.data.model.Todo
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import kotlinx.android.synthetic.main.todo_new_item.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel



class AddTodoFragment : DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.todoList.observe(this, Observer {
            if(it != null) updateTodoList(it)
        })

        todo_form_save_button.setOnClickListener {
            if(validateFields()) {
                val name = todo_edit_name.editText?.text.toString()
                val description = todo_edit_description.editText?.text.toString()
                val todo = Todo(title = name, description = description)
                viewModel.saveTodo(todo)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.todo_new_item, container, false)
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (todo_create_name.text!!.isEmpty()) {
            todo_create_name.error = "Favor inserir o nome"
            todo_create_name.requestFocus()
            return false
        }
        if (todo_create_description.text!!.isEmpty()) {
            todo_create_description.error = "Favor inserir a descrição"
            todo_create_description.requestFocus()
            return false
        }
        return true
    }
}