package com.resende.todolist.presentation.todo.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.resende.todolist.R
import com.resende.todolist.data.model.Todo
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import kotlinx.android.synthetic.main.todo_edit_item_dialog_fragment.*
import kotlinx.android.synthetic.main.todo_edit_item_dialog_fragment.todo_edit_name
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class EditTodoFragment : DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by sharedViewModel()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val extra: Bundle? = arguments

        viewModel.todoList.observe(this, Observer {
            if (it != null) updateTodoList(it)
        })
        todo_edit_todo_name.setText(extra?.getString("edit_todo_name"))
        todo_edit_todo_description.setText(extra?.getString("edit_todo_description"))

        todo_form_edit_button.setOnClickListener {
            if(validateFields()) {
                val nameEdit = todo_edit_todo_name.text.toString()
                val descriptionEdit = todo_edit_todo_description.text.toString()
                val id = extra?.getInt("edit_todo_id")
                val editedTodo = Todo(id = id!!, title = nameEdit, description = descriptionEdit)
                viewModel.updateTodo(editedTodo)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.todo_edit_item_dialog_fragment, container, false)
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (todo_edit_todo_name.text!!.isEmpty()) {
            todo_edit_todo_name.error = "Favor inserir o nome"
            todo_edit_name.requestFocus()
            return false
        }
        if (todo_edit_todo_description.text!!.isEmpty()) {
            todo_edit_todo_description.error = "Favor inserir a descrição"
            todo_edit_todo_description.requestFocus()
            return false
        }
        return true
    }

}