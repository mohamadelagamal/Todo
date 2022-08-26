package todo.ui.details

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.BaseViewModel
import com.example.usercase.repos.SourceOfflineRepository
import com.example.usercase.room.Todo
import com.route.todo_c35_sat.database.MyDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import todo.ui.DataBase.clearTime
import java.util.*
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor
    (var sourceOfflineRepository: SourceOfflineRepository):BaseViewModel<Navigator>() {
    lateinit var room: Todo
    val title = ObservableField<String>()
    val details =ObservableField<String>()
    val choiceData = ObservableField<String>()
    val calendar = Calendar.getInstance()

    fun goToMain(){
        navigator?.goToMainActivity()
    }

    fun saveChange(){
       // navigator?.saveChange()
        val title = title.get().toString();
        val details = details.get().toString();

        InsertTodo_DataBase(title,details)
        MyDataBase.getInstance(context)
    }
    fun choiceCalendar(){
        showDatePicker()
    }
    fun showDatePicker() {
        val datePicker = DatePickerDialog(
            context,
            { p0, year, month, day ->
                calendar.set(Calendar.DAY_OF_MONTH, day)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                choiceData.set("" + day + "/" + (month + 1) + "/" + year)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
    fun InsertTodo_DataBase( title : String,details:String) {
        viewModelScope.launch {
            val entity_class=Todo(id=room.id, name =title, details = details,
                date = calendar.clearTime().time, isDone = room.isDone)
           // sourceOfflineRepository = SourcesOfflineDataSourceImpl(MyDataBase.getInstance(context))
            sourceOfflineRepository.updateDate(entity_class)
            Toast.makeText(context, "Todo added successfully", Toast.LENGTH_LONG)
                .show();
            // call back to activity to notify insertion
            activity.onBackPressed()
            onTodoAddedListener?.onTodoAdded()
            activity.finish()
        }


    }
    var onTodoAddedListener: OnTodoAddedListener? = null;

    interface OnTodoAddedListener {
        fun onTodoAdded();
    }
}