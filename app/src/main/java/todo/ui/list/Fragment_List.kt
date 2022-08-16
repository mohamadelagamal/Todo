package todo.ui.list

import android.content.Intent
import todo.ui.DataBase.clearTime
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.model.Todo
import todo.model.Constant
import todo.ui.list.adapter.Todo_Recyecler_Adapter_List
import todo.ui.R
import todo.ui.databinding.FragmentListBinding
import todo.ui.details.DetailsTodoActivity
import java.util.*

class Fragment_List: Fragment() {
    lateinit var viewDataBinding:FragmentListBinding
    val adapter= Todo_Recyecler_Adapter_List(null)
    var date=Calendar.getInstance()
    //make object about calander
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        //... root is the root element inflation
        return viewDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        getTodoFromDatabase()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialzationItem()
        adapter.onItemClickListener = object : Todo_Recyecler_Adapter_List.OnItemClickListener {
            override fun onItemClick(pos: Int, room: Todo) {
                // send data
                startChatActiviy(room)
            }
        }
    }

     fun getTodoFromDatabase() {
        val todoList=MyDataBase.getInstance(requireContext())
                //.todoDao().getAllTodos()
                .todoDao().getTodosByDate(date.clearTime().time)
            adapter.ChangeData(todoList.toMutableList())
        }
    private fun initialzationItem() {
        viewDataBinding.RecyclerHome.adapter=adapter
        viewDataBinding.calendarViewHome.selectedDate= CalendarDay.today();

        viewDataBinding.calendarViewHome.setOnDateChangedListener { widget, calenderDay, selected ->
            Log.e("calender day of monthe",""+calenderDay.month)
            date.set(Calendar.DAY_OF_MONTH,calenderDay.day)
            date.set(Calendar.MONTH,calenderDay.month-1)
            date.set(Calendar.YEAR,calenderDay.year)
            getTodoFromDatabase()
        }

    }

    private fun startChatActiviy(room: Todo) {
        val intent = Intent(requireContext(), DetailsTodoActivity::class.java)
        // send data
        intent.putExtra(Constant.EXTRA_ROOM, room)
        startActivity(intent)
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}