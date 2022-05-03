package todo.model.base
import androidx.lifecycle.ViewModel
abstract class BaseViewModel<N>:ViewModel(){
    var navigator:N?=null
}