package todo.ui.fragment.list
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.zerobranch.layout.SwipeLayout
import todo.database.date.clearTime
import todo.database.entity.Todo
import todo.database.table.MyDatabase
import todo.ui.R
import todo.ui.fragment.Constants
import todo.ui.fragment.list.adapter.Todo_Recyecler_Adapter_List
import todo.ui.fragment.update.UpdatedTodoActivity
import java.util.*

class Fragment_List: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }
    lateinit var recyclerView: RecyclerView
    //make object about calandar
    lateinit var calendarView:MaterialCalendarView
    val adatpter= Todo_Recyecler_Adapter_List(null)
    override fun onResume() {
        super.onResume()
        getTodoFromDatabase()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialzationItem()
    }

    var date=Calendar.getInstance()


 fun getTodoFromDatabase() {
//     clearCalendarTime()

   // date.clear()

    val todoList=MyDatabase.getInsertion(requireContext())
            //.todoDao().getAllTodos()
            .todoDao().getTodoDate(date.clearTime().time)
        adatpter.ChangeData(todoList.toMutableList())    }

//    private fun clearCalendarTime() {
//        date.clear(Calendar.HOUR)
//        date.clear(Calendar.MILLISECOND)
//        date.clear(Calendar.SECOND)
//        date.clear(Calendar.MINUTE)
//    }

    private fun initialzationItem() {
        recyclerView=requireView().findViewById(R.id.RecyclerHome)
        adatpter.onItemclikListener = object : Todo_Recyecler_Adapter_List.onItemClikListener {
            override fun onItemclike(pos: Int, name: Todo) {
                var intent = Intent(requireContext(),UpdatedTodoActivity::class.java)
                //.. don't forget add implementation in todo Serializable
                intent.putExtra(Constants.EXTRA_TodoUpdated,name)
                startActivity(intent)
            }

        }
        recyclerView.adapter=adatpter
      //  onItemClickedToBeUpdated()
        //onLongLister()
        // findViewById to calender
        calendarView=requireActivity().findViewById(R.id.calendarView_Home)
        calendarView.selectedDate= CalendarDay.today();
        calendarView.setOnDateChangedListener { widget, calenderDay, selected ->
            Log.e("calender day of monthe",""+calenderDay.month)
            date.set(Calendar.DAY_OF_MONTH,calenderDay.day)
            date.set(Calendar.MONTH,calenderDay.month-1)
            date.set(Calendar.YEAR,calenderDay.year)
            getTodoFromDatabase()
        }
    }

    fun onItemClickedToBeUpdated(){
        adatpter.onItemClickedToUpdated= object :Todo_Recyecler_Adapter_List.OnItemClickedToNBUpdated{
            override fun onItemClickedToUpdate(todo: Todo) {

            }

            override fun onItemClickedTOBeDeleted(position: Int) {
                // take action
            }
        }

    }
    fun onLongLister(){
        adatpter.onItemLongClick = object : Todo_Recyecler_Adapter_List.setOnLongClickListener{
            override fun onItemClickLong(todo: Todo) {
                var intent = Intent(requireContext(),UpdatedTodoActivity::class.java)
                //.. don't forget add implementation in todo Serializable
                intent.putExtra(Constants.EXTRA_TodoUpdated,todo)
                startActivity(intent)
                // Toast.makeText(this,"77777777777 " , Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onStart() {
        super.onStart()

    }

}