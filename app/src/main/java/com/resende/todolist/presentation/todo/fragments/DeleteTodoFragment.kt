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
import kotlinx.android.synthetic.main.todo_delete_item_dialog_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DeleteTodoFragment: DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.todoList.observe(this, Observer {
            if(it != null) updateTodoList(it)
        })

        todo_form_delete_button.setOnClickListener{
            if(validateFields()) {
                val todoId = todo_delete_id_field.text.toString().toLong()
                viewModel.deleteTodo(todoId)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.todo_delete_item_dialog_fragment, container, false)
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (todo_delete_id_field.text!!.isEmpty()) {
            todo_delete_id_field.error = "Insira o número da atividade a ser deletada"
            todo_delete_id_field.requestFocus()
            return false
        }
        return true
    }
}