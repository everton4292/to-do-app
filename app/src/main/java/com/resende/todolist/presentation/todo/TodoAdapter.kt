package com.resende.todolist.presentation.todo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.resende.todolist.data.model.Todo
import com.resende.todolist.databinding.TodoItemBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todosList: MutableList<Todo> = mutableListOf()
    lateinit var clickTodoListener: OnLongClickTodoListener

    inner class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.todoItemName.text = todo.title
            binding.todoItemDescription.text = todo.description
            binding.todoItemID.text = todo.id.toString()

            binding.root.setOnLongClickListener {
                clickTodoListener.onClick(todo)
                true
            }
        }
    }

    fun getTodo(todoList: List<Todo>) {
        todosList.clear()
        todosList.addAll(todoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    private fun getItem(position: Int): Todo {
        return todosList[position]
    }

    inline fun setOnClickTodoListener(crossinline listener: (Todo) -> Unit) {
        this.clickTodoListener = object :
            OnLongClickTodoListener {
            override fun onClick(todo: Todo) = listener(todo)
        }
    }

    interface OnLongClickTodoListener {
        fun onClick(todo: Todo) = Unit
    }
}

