package todo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity
data class Todo (
    //...to make insert data in sqlite
    @PrimaryKey(autoGenerate = true)
    //..to make data in column in Sqlite
    @ColumnInfo
    var id : Int?=null,
    @ColumnInfo
    var name:String?=null,
    @ColumnInfo
    var details:String?=null,
    @ColumnInfo
    var date:Date?=null,
    @ColumnInfo
    var isDone:Boolean?=false
        ):Serializable{

}