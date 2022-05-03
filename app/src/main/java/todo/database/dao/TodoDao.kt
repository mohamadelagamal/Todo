package todo.database.dao

import androidx.room.*
import todo.database.entity.Todo
import java.util.*

//.. data access objects This is the interface(الوسيط) between user and Sqlite
@Dao
interface TodoDao {
    //..make insertion
    @Insert
    fun addTodo(todo:Todo)
    @Update
    fun updateTodo(todo: Todo)
    @Delete
    fun deleteTodo(todo: Todo)
    @Query("select * from Todo")
    fun getTodos():List<Todo>
    @Query("select * from Todo where date=:date")
    fun getTodoDate(date: Date):List<Todo>
}