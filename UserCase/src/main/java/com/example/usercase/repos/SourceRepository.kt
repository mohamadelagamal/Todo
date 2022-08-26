package com.example.usercase.repos


import androidx.room.*
import com.example.usercase.room.Todo
import java.util.*

interface SourceOfflineRepository {
    // get data from catch (Room)
    suspend fun getSourceFromData(data:Date):List<Todo>
    suspend fun updateDate(todo:Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun addTodo(todo: Todo)
}