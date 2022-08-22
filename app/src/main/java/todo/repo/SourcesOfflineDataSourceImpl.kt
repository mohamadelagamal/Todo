package todo.repo

import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.model.Todo
import java.util.*
import javax.inject.Inject

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
