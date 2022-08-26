package com.example.entities.repos

import com.route.todo_c35_sat.database.model.TodoDTO
import java.util.*

interface SourceOfflineRepositoryDTO{
    // get data from catch (Room)
    suspend fun getSourceFromData(data:Date):List<TodoDTO>
    suspend fun updateDate(todo:TodoDTO)
    suspend fun deleteTodo(todo: TodoDTO)
    suspend fun addTodo(todo: TodoDTO)
}