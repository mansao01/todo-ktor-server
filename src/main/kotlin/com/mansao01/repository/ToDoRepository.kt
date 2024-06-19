package com.mansao01.repository

import com.mansao01.entity.ToDo
import com.mansao01.entity.ToDoDraft

interface ToDoRepository {
    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

    fun addToDo(draft: ToDoDraft): ToDo

    fun removeToDo(id: Int): Boolean

    fun updateToDo(id: Int, draft: ToDoDraft): Boolean
}