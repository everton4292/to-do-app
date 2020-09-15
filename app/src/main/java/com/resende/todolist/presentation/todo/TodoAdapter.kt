package com.resende.todolist.presentation.todo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.resende.todolist.R
import com.resende.todolist.data.model.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    private val todosList: MutableList<Todo> = mutableListOf()
    lateinit var clickTodoListener: OnLongClickTodoListener

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(todo: Todo) {
            itemView.todo_item_name.text = todo.title
            itemView.todo_item_description.text = todo.description
            itemView.todo_item_ID.text = todo.id.toString()

            itemView.setOnLongClickListener {
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

        val cellForRow =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(cellForRow)
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

