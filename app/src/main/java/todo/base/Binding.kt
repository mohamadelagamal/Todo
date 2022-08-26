package com.example.usercase.model

import com.google.android.material.textfield.TextInputLayout
import java.lang.Error

@androidx.databinding.BindingAdapter("app:error")
fun setError(textInput:TextInputLayout,error: String){
    textInput.error = error
}