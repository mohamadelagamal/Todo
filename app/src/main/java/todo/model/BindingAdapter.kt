package todo.model

import com.google.android.material.textfield.TextInputLayout


@androidx.databinding.BindingAdapter("app:error")
fun setError(textInputLayout: TextInputLayout, error:String){
    textInputLayout.error=error
}