package com.resende.todolist.presentation.todo.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.resende.todolist.data.model.Todo
import com.resende.todolist.databinding.TodoDeleteItemDialogFragmentBinding
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DeleteTodoFragment : DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by activityViewModel()
    private lateinit var binding: TodoDeleteItemDialogFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.todoList.observe(this, Observer {
            if (it != null) updateTodoList(it)
        })

        binding.todoFormDeleteButton.setOnClickListener {
            if (validateFields()) {
                val todoId = binding.todoDeleteIdField.text.toString().toLong()
                viewModel.deleteTodo(todoId)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = TodoDeleteItemDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (binding.todoDeleteIdField.text!!.isEmpty()) {
            binding.todoDeleteIdField.error = "Insira o número da atividade a ser deletada"
            binding.todoDeleteIdField.requestFocus()
            return false
        }

        return true
    }
}