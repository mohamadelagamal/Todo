package todo.database.table
import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import todo.database.dao.TodoDao
import todo.database.date.DateConverter
import todo.database.entity.Todo
import todo.model.Constant

//..set data in table DataBase
@Database(entities = [Todo::class], version = 1)
//..add date in database
@TypeConverters(DateConverter::class)
abstract class MyDatabase:RoomDatabase() {
    //.. take object from Data access objects
    abstract fun todoDao():TodoDao
    //... to set table static *
    companion object{
        //... make table name in dataBase
        private var myDatabase:MyDatabase?=null
        //... make insertion
        fun getInsertion(context:Context):MyDatabase{
            //...take object from database to check about Existing data (used singleton pattern)
            when (myDatabase) {
                null -> {
                    myDatabase = Room.databaseBuilder(context,MyDatabase::class.java, Constant.DATABASE_NAME)
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return myDatabase!!
        }
    }
}