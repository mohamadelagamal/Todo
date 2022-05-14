package todo.ui.fragment.update

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputLayout
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import todo.database.date.clearTime
import todo.database.entity.Todo
import todo.database.table.MyDatabase
import todo.ui.R
import todo.ui.fragment.Constants
import java.util.*

class UpdatedTodoActivity : AppCompatActivity() {
    lateinit var titleUpdate : EditText
    lateinit var descUpdate : EditText
    lateinit var dateUpdate : TextView
    lateinit var saveUpdate:Button
    lateinit var titleLayout:TextInputLayout
    lateinit var detailsLayout: TextInputLayout
    lateinit var task:Todo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updated_todo)
        initViews()
        //receive Data from PutExtra()
        receivedData()

    }

    private fun receivedData() {
        let {
            task= ((intent.getSerializableExtra(Constants.EXTRA_TodoUpdated) as? Todo)!!)
        }
        titleUpdate.setText(task.name)
        descUpdate.setText(task.details)
        dateUpdate.setText(task.date.toString())
    }

    private fun initViews() {
        saveUpdate = findViewById(R.id.saveButton)
        titleUpdate = findViewById(R.id.titleEdit)
        descUpdate = findViewById(R.id.detailsEdit)
        dateUpdate = findViewById(R.id.dataID)
        dateUpdate.setText(""+calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR))
        titleLayout = findViewById(R.id.titleInput)
        detailsLayout = findViewById(R.id.descriptionInput)
        //save data
        saveUpdate.setOnClickListener {
            if (valid()){
                //...updateData
                val title = titleLayout.editText?.text.toString()
                val details = detailsLayout.editText?.text.toString()
                val date = Calendar.getInstance().clearTime().time
                //...take object from todo to save it in dataBase
                //....take id about Item form task
                val todoUpdated = Todo(task.id,title,details,date)
                updateDatabase(todoUpdated)            }
        }
        dateUpdate.setOnClickListener {
            showDatePicker()
        }
    }

    private fun updateDatabase(todo: Todo) {
//        var newTodo = Todo(task.id,titleUpdate,descUpdate,Calendar.getInstance().clearTime().time,false)
        MyDatabase.getInsertion(this).todoDao().updateTodo(todo)
        Toast.makeText(this,"Updated Successful",Toast.LENGTH_LONG).show()
        //.. to back in list todo
        onBackPressed()
    }


    fun valid(): Boolean {
        var isValid = true;
        if (titleLayout.editText?.text.toString().isBlank()) {
            titleLayout.error = "please enter todo title";
            isValid = false;
        } else {
            titleLayout.error = null
        }
        if (detailsLayout.editText?.text.toString().isBlank()) {
            detailsLayout.error = "please enter todo details";
            isValid = false;
        } else {
            detailsLayout.error = null
        }
        return isValid;
    }
    //...take object from Calender
    val calendar = Calendar.getInstance()
    fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { p0, year, month, day ->
                calendar.set(Calendar.DAY_OF_MONTH, day)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                dateUpdate.setText("" + day + "/" + (month + 1) + "/" + year)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()

    }

}