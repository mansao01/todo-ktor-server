package com.mansao01.repository

import com.mansao01.entity.ToDo
import com.mansao01.entity.ToDoDraft

class ToDoRepositoryImp : ToDoRepository {

    private val todos = mutableListOf(
        ToDo(1, "Kotlin", true),
        ToDo(2, "Compose", true),
    )

    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull { it.id == id }
    }

    override fun addToDo(draft: ToDoDraft): ToDo {
        val todo = ToDo(
            id = todos.size + 1,
            title = draft.title,
            done = draft.done
        )
        todos.add(todo)
        return todo
    }

    override fun removeToDo(id: Int): Boolean {
        return todos.removeIf { it.id == id }

    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        val todo = todos.firstOrNull { it.id == id } ?: return false

        todo.title = draft.title
        todo.done = draft.done
        return true
    }
}