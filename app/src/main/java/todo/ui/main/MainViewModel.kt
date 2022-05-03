package todo.ui.main
import todo.model.base.BaseViewModel


class MainViewModel : BaseViewModel<Navigator>() {
    fun showDialog(){
    navigator?.showDialog()
    }

}