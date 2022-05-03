package todo.ui.main
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import todo.model.base.BaseActivity
import todo.ui.R
import todo.ui.databinding.ActivityMainBinding
import todo.ui.fragment.dialoge.FrameDialoge_Add
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(),Navigator {
    val AddBottonSheet= FrameDialoge_Add()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vmHome=viewModel
        viewModel.navigator=this
    }
    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }
    override fun makeViewModelProvider(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun showDialog() {
            AddBottonSheet.show(supportFragmentManager,"")
            AddBottonSheet.onTodoAddedListener=object: FrameDialoge_Add.OnTodoAddedListener{
                override fun onTodoAdded() {
                    // Refresh to Todos list From database inside listFragment
                    if (Todo_Frame_List.isVisible)
                        Todo_Frame_List.getTodoFromDatabase()
                }
            }
    }
}