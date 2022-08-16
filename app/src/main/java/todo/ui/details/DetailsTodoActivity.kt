package todo.ui.details

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.dao.TodoDao
import com.route.todo_c35_sat.database.model.Todo
import todo.model.Constant
import todo.ui.DataBase.clearTime
import todo.ui.R
import todo.ui.databinding.ActivityDetailsTodoBinding
import todo.ui.databinding.FragmentListBinding
import todo.ui.home.MainActivity
import java.util.*

class DetailsTodoActivity : AppCompatActivity() {
    lateinit var room:Todo
    lateinit var viewDataBinding: ActivityDetailsTodoBinding
    // to sorted messages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_details_todo)
        room = intent.getParcelableExtra(Constant.EXTRA_ROOM)!!
        viewDataBinding.title.setText(room.name)
        viewDataBinding.details.setText(room.details)
        viewDataBinding.ChoseDate.setText(room.date.toString())
        viewDataBinding.line1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }
        viewDataBinding.ChoseDate.setOnClickListener {
            showDatePicker()
        }
        viewDataBinding.saveChanges.setOnClickListener{
            val title = viewDataBinding.title.text.toString();
            val details = viewDataBinding.details.text.toString();

            InsertTodo_DataBase(title,details)
            MyDataBase.getInstance(this)

        }
    }
    val calendar = Calendar.getInstance()
    fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { p0, year, month, day ->
                calendar.set(Calendar.DAY_OF_MONTH, day)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
               viewDataBinding.ChoseDate.setText("" + day + "/" + (month + 1) + "/" + year)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()

    }
    private fun InsertTodo_DataBase( title : String,details:String) {
        val entity_class=Todo(id=room.id, name =title, details = details,
            date = calendar.clearTime().time, isDone = room.isDone)
        MyDataBase.getInstance(this)
            .todoDao().updateTodo(entity_class)
        Toast.makeText(this, "Todo added successfully", Toast.LENGTH_LONG)
            .show();
        // call back to activity to notify insertion
        onBackPressed()
        onTodoAddedListener?.onTodoAdded()
        finish()
    }
    var onTodoAddedListener: OnTodoAddedListener? = null;

    interface OnTodoAddedListener {
        fun onTodoAdded();
    }
}