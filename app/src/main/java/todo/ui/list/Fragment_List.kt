package todo.ui.list

import android.content.Intent
import todo.ui.DataBase.clearTime
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.usercase.model.Constant
import com.example.usercase.repos.SourceOfflineRepository
import com.example.usercase.room.Todo
import todo.ui.list.adapter.Todo_Recyecler_Adapter_List
import todo.ui.R
import todo.ui.databinding.FragmentListBinding
import todo.ui.details.DetailsTodoActivity
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class Fragment_List: Fragment() {
    lateinit var viewDataBinding:FragmentListBinding
    @Inject lateinit var sourceOfflineRepository: SourceOfflineRepository
    val adapter= Todo_Recyecler_Adapter_List(null)
    var date=Calendar.getInstance()
    // make object about calander
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        //... root is the root element inflation
        return viewDataBinding.root
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

            override fun onItemDelete(pos: Int, room: Todo) {
                lifecycleScope.launch {
                    sourceOfflineRepository.deleteTodo(room)
                }
                Toast.makeText(requireContext(), "successfully deleted", Toast.LENGTH_LONG)
                    .show();
               getTodoFromDatabase()
            }

            override fun makeDone(pos: Int, room: Todo) {
                val entity_class=Todo(id=room.id, name =room.name, details = room.details,
                    date = room.date, isDone = true)
                lifecycleScope.launch{
                    sourceOfflineRepository.updateDate(entity_class)
                }
            }
        }
    }

      fun getTodoFromDatabase() {
        //  sourceOfflineRepository= SourcesOfflineDataSourceImpl(MyDataBase.getInstance(requireContext()))

          lifecycleScope.launch {
              val todoList = sourceOfflineRepository
                  .getSourceFromData(data = date.clearTime().time)
              adapter.ChangeData(todoList.toMutableList())
          }


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