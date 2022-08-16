package todo.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.todo_c35_sat.database.model.Todo
import todo.ui.R
import todo.ui.databinding.ItemTodoRecycleBinding

class Todo_Recyecler_Adapter_List(var items:MutableList<Todo>?)  : RecyclerView.Adapter<Todo_Recyecler_Adapter_List.viewHolder>() {

    class viewHolder(val itemBinding: ItemTodoRecycleBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item:Todo?){
            itemBinding.vmTodo=item
            itemBinding.invalidateAll()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun ChangeData(newItems:MutableList<Todo>){
        items=newItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewDataBinding : ItemTodoRecycleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),R.layout.item_todo_recycle,parent,false
        )
        return viewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items!!.get(position)
        holder.bind(item)
        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                // position this is number in onBindViewHolder and items[position] this number in list
                onItemClickListener?.onItemClick(position, items!![position])
            }
        }
    }

    // get position in list and details items in room and we used interface because we will use it in homeActivity
    var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClick(pos: Int, room: Todo)
    }
    override fun getItemCount(): Int =items?.size ?:0
}