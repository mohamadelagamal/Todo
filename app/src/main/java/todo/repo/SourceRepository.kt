package todo.repo

import com.route.todo_c35_sat.database.model.Todo

import androidx.room.*
import java.util.*

interface SourceOfflineRepository {
    // get data from catch (Room)
    suspend fun getSourceFromData(data:Date):List<Todo>
    suspend fun updateDate(todo:Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun addTodo(todo: Todo)
}