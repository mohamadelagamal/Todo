package todo.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import todo.model.base.BaseActivity
import todo.ui.R
import todo.ui.databinding.ActivityMainBinding
import todo.ui.fragment.dialoge.FrameDialoge_Add


class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(),Navigator {

    lateinit var tvSelected:MeowBottomNavigation
    companion object {
        private const val ID_HOME = 1
        private const val ID_EXPLORE = 2
        private const val ID_NOTIFICATION = 3
    }
    val AddBottonSheet= FrameDialoge_Add()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vmHome=viewModel
        viewModel.navigator=this
         tvSelected = viewDataBinding.NavigationHome

        viewDataBinding.NavigationHome.apply {

            add(
                MeowBottomNavigation.Model(
                    ID_HOME,
                    R.drawable.ic_baseline_dashboard_24
                )
            )
            add(
                MeowBottomNavigation.Model(
                    ID_EXPLORE,
                    R.drawable.ic_baseline_add_24
                )
            )
            add(
                MeowBottomNavigation.Model(
                    ID_NOTIFICATION,
                    R.drawable.ic_settings
                )
            )
            setCount(ID_EXPLORE, "task")
            setOnShowListener {
                val name = when (it.id) {
                    ID_HOME -> "HOME"
                    ID_EXPLORE -> "EXPLORE"
                    ID_NOTIFICATION -> "NOTIFICATION"
                    else -> ""
                }

            }
            show(ID_HOME)
            setOnClickMenuListener {
               if (it.id==1){
                   pushFragment(Todo_Frame_List)
               }
                else if (it.id==2){
                    showDialog()
                }
                else if (it.id==3){
                    pushFragment(Todo_Frame_Setting)
                }
            }
//            setOnReselectListener {
//                Toast.makeText(context, "item ${it.id} is reselected.", Toast.LENGTH_LONG).show()
//            }

            show(ID_HOME)
        }

    }



    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FrameLayoutHome,fragment).commit()
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