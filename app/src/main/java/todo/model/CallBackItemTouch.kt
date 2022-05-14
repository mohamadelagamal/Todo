package todo.model

import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

interface CallBackItemTouch {
    fun itemTuchOnMove(oldPosition :Int , newPosition: Int)
    fun onSwiped(viewHolder: RecyclerView.ViewHolder,position:Int)
}