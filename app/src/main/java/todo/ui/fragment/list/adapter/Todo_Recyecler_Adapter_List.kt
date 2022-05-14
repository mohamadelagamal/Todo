package todo.ui.fragment.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import todo.database.entity.Todo
import todo.ui.R


class Todo_Recyecler_Adapter_List(var items:MutableList<Todo>?)  : RecyclerView.Adapter<Todo_Recyecler_Adapter_List.viewHolder>() {

    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title :TextView= itemView.findViewById(R.id.title_Done)
        val descraption:TextView=itemView.findViewById(R.id.description_Done)
        val makeDone:ImageView=itemView.findViewById(R.id.make_Done)
        val rightView:ImageView = itemView.findViewById(R.id.right_view)
    }
    fun ChangeData(newItems:MutableList<Todo>){
        items=newItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_todo_recycle,parent,false)
        return viewHolder(view)
    }
    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items!!.get(position)
        holder.title.setText(item.name)
        holder.descraption.setText(item.details)
        if(onItemclikListener!=null){
            holder.itemView.setOnClickListener{
                onItemclikListener?.onItemclike(position,item)
            }
        }

    }
    override fun getItemCount(): Int =items?.size ?:0
    var onItemClickedToUpdated:OnItemClickedToNBUpdated?=null
    interface OnItemClickedToNBUpdated{
        fun onItemClickedToUpdate(todo: Todo)
        fun onItemClickedTOBeDeleted(position: Int)
    }
    var onItemLongClick : setOnLongClickListener?=null
    interface setOnLongClickListener {
        fun onItemClickLong(todo: Todo)
    }

    var onItemclikListener:onItemClikListener?=null
    interface onItemClikListener{
        fun onItemclike(pos:Int, name: Todo)
    }
}