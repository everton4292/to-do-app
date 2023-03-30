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
import com.resende.todolist.databinding.TodoDeleteItemDialogFragmentBinding
import com.resende.todolist.databinding.TodoEditItemDialogFragmentBinding
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class EditTodoFragment : DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by activityViewModel()
    private lateinit var binding: TodoEditItemDialogFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val extra: Bundle? = arguments

        viewModel.todoList.observe(this, Observer {
            if (it != null) updateTodoList(it)
        })

        binding.todoEditTodoName.setText(extra?.getString("edit_todo_name"))
        binding.todoEditTodoDescription.setText(extra?.getString("edit_todo_description"))

        binding.todoFormEditButton.setOnClickListener {
            if(validateFields()) {
                val nameEdit = binding.todoEditTodoName.text.toString()
                val descriptionEdit = binding.todoEditTodoDescription.text.toString()
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
        binding = TodoEditItemDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (binding.todoEditTodoName.text!!.isEmpty()) {
            binding.todoEditTodoName.error = "Favor inserir o nome"
            binding.todoEditTodoName.requestFocus()
            return false
        }
        if (binding.todoEditTodoDescription.text!!.isEmpty()) {
            binding.todoEditTodoDescription.error = "Favor inserir a descrição"
            binding.todoEditTodoDescription.requestFocus()
            return false
        }
        return true
    }

}