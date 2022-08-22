package todo.repo

import android.content.Context
import com.route.todo_c35_sat.database.MyDataBase
import com.route.todo_c35_sat.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {
    @Provides
    @Singleton
    // get repository
    fun provideOfflineDataSource(dataBase: MyDataBase):SourceOfflineRepository{
        return SourcesOfflineDataSourceImpl(dataBase)
    }
    @Singleton
    @Provides
    fun provideDatabase():MyDataBase{
        return MyDataBase.returnRoom()
    }
}