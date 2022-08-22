package com.base

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<N>:ViewModel(){
    var navigator:N?=null
    val showLoading = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    lateinit var context: Context
    lateinit var activity: Activity

}