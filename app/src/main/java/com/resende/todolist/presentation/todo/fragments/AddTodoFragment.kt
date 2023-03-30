package com.resende.todolist.presentation.todo.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer;
import com.resende.todolist.data.model.Todo
import com.resende.todolist.databinding.TodoNewItemBinding
import com.resende.todolist.presentation.todo.TodoAdapter
import com.resende.todolist.presentation.todo.TodoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AddTodoFragment : DialogFragment() {

    private val todoAdapter: TodoAdapter by inject()
    private val viewModel: TodoViewModel by activityViewModel()
    private lateinit var binding: TodoNewItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.todoList.observe(this, Observer {
            if(it != null) updateTodoList(it)
        })

        binding.todoFormSaveButton.setOnClickListener {
            if(validateFields()) {
                val name = binding.todoEditName.editText?.text.toString()
                val description = binding.todoEditDescription.editText?.text.toString()
                val todo = Todo(title = name, description = description)
                viewModel.saveTodo(todo)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = TodoNewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateTodoList(todoList: List<Todo>) {
        todoAdapter.getTodo(todoList)
    }

    private fun validateFields(): Boolean {
        if (binding.todoCreateName.text!!.isEmpty()) {
            binding.todoCreateName.error = "Favor inserir o nome"
            binding.todoCreateName.requestFocus()
            return false
        }
        if (binding.todoCreateDescription.text!!.isEmpty()) {
            binding.todoCreateDescription.error = "Favor inserir a descrição"
            binding.todoCreateDescription.requestFocus()
            return false
        }
        return true
    }
}