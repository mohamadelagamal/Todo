package todo.repo

import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.model.Todo
import dagger.hilt.android.scopes.FragmentScoped
import java.util.*
import javax.inject.Inject

@FragmentScoped
class SourcesOfflineDataSourceImpl @Inject constructor (val myDataBase: MyDataBase)
    :SourceOfflineRepository{

    override suspend fun getSourceFromData(data: Date): List<Todo> {
        return myDataBase.todoDao().getTodosByDate(data)
    }

    override suspend fun updateDate(todo: Todo) {
        return myDataBase.todoDao().updateTodo(todo)
    }

}