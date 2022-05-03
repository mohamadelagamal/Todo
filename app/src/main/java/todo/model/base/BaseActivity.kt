package todo.model.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import todo.ui.R
import todo.ui.fragment.list.Fragment_List
import todo.ui.fragment.setting.Fragment_Setting

abstract class BaseActivity  <DB: ViewDataBinding,VM: BaseViewModel<*>>: AppCompatActivity(){
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    lateinit var MainNavigation: BottomNavigationView
    val Todo_Frame_List= Fragment_List()
    val Todo_Frame_Setting= Fragment_Setting()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutID())
        viewModel = makeViewModelProvider()
        MainNavigation=findViewById(R.id.NavigationHome)
//        findViewById<BottomNavigationView>(R.id.NavigationHome).apply {
//            text = viewModel.
//        }
        NavigationItems()
    }
    abstract fun getLayoutID():Int
    abstract fun makeViewModelProvider():VM
    private fun NavigationItems() {
        MainNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            if (it.itemId==R.id.setting_Navigation){
                pushFragment(Todo_Frame_Setting)
            }
            else if (it.itemId==R.id.details_Navigation){
                pushFragment(Todo_Frame_List)
            }
            return@OnItemSelectedListener true
        })
        MainNavigation.selectedItemId=R.id.details_Navigation
    }
    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FrameLayoutHome,fragment).commit()
    }
}