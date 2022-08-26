package com.example.usercase.repos

import com.example.usercase.room.Todo
import com.route.todo_c35_sat.database.MyDataBase
import java.util.*

class SourcesOfflineDataSourceImpl (val myDataBase: MyDataBase)
    :SourceOfflineRepository{
    override suspend fun getSourceFromData(data: Date): List<Todo> {
        return myDataBase.todoDao().getTodosByDate(data)
    }
    override suspend fun updateDate(todo: Todo) {
        return myDataBase.todoDao().updateTodo(todo)
    }
    override suspend fun deleteTodo(todo: Todo) {
        return myDataBase.todoDao().deleteTodo(todo)
    }
    override suspend fun addTodo(todo: Todo) {
        return myDataBase.todoDao().addTodo(todo)
    }
}
