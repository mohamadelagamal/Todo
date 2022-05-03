package todo.ui.fragment.dialoge

import android.app.DatePickerDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import todo.database.date.clearTime
import todo.database.entity.Todo
import todo.database.table.MyDatabase
import java.util.*

class DialogViewModel:ViewModel() {
    var titleLayout = ObservableField<String>()
    var detailsLayout = ObservableField<String>()
    var chooseDate = ObservableField<String>()
    var titleError = ObservableField<String>()
    var detailsError = ObservableField<String>()
    val calendar = Calendar.getInstance()

    fun validation():Boolean{
        var valid = true
        if (titleLayout.get().isNullOrBlank()){
            titleError.set("please enter your email")
            valid=false
        }else{
            titleError.set(null)
        }
        if (detailsLayout.get().isNullOrBlank()){
            detailsError.set("please enter your password")
            valid=false
        }else{
            detailsError.set(null)
        }
        return valid
    }

}