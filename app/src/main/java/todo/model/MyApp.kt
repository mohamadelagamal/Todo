package todo.model

import android.app.Application
import com.route.todo_c35_sat.database.MyDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        MyDataBase.getInstance(this)
    }
}