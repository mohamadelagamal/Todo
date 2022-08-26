package todo.ui.list.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usercase.room.Todo
import todo.ui.R
import todo.ui.databinding.ItemTodoRecycleBinding

class Todo_Recyecler_Adapter_List(var items:MutableList<Todo>?)
    : RecyclerView.Adapter<Todo_Recyecler_Adapter_List.viewHolder>() {

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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items!!.get(position)
        holder.bind(item)


        onItemClickListener?.let {
            holder.itemBinding.editID.setOnClickListener {
                onItemClickListener?.onItemClick(position, items!![position])
            }
            holder.itemBinding.textDelete.setOnClickListener {
                onItemClickListener?.onItemDelete(position, items!![position])
            }
            holder.itemBinding.makeDone.setOnClickListener {
                holder.itemBinding.makeDone.isVisible = false
                holder.itemBinding.makeDoneTextView.setText("Done!")
                holder.itemBinding.titleDone.setTextColor(Color.GREEN)
                onItemClickListener?.makeDone(position, items!![position])


            }
        }
        if (item.isDone==true){
            holder.itemBinding.makeDone.isVisible = false
            holder.itemBinding.makeDoneTextView.setText("Done!")
            holder.itemBinding.titleDone.setTextColor(Color.GREEN)
            Log.e("item.name",item.name.toString())

        }
    }

    // get position in list and details items in room and we used interface because we will use it in homeActivity
    var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClick(pos: Int, room: Todo)
        fun onItemDelete(pos: Int, room: Todo)
        fun makeDone(pos: Int, room: Todo)

    }
    override fun getItemCount(): Int =items?.size ?:0
}