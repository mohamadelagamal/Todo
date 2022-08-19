//package todo.repo
//
//import com.route.todo_c35_sat.database.MyDataBase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.FragmentComponent
//import dagger.hilt.android.scopes.FragmentScoped
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object SourcesModule {
//
//    @Provides
//    @Singleton
//    // get repository
//    fun provideOfflineDataSource(dataBase: MyDataBase):SourceOfflineRepository{
//        return SourcesOfflineDataSourceImpl(dataBase)
//    }
//}