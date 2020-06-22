package develop.alex.android.di.modules

import dagger.Module
import dagger.Provides
import develop.alex.android.data.ApiGitHub
import develop.alex.android.data.repository.ListUsersRepository
import develop.alex.android.data.repository.LoginRepository
import develop.alex.android.data.repository.MainRepository
import develop.alex.android.data.repository.UserDetailsRepository
import javax.inject.Singleton

@Module
class DataModule {

    /**
     * Repository
    * */
    @Singleton
    @Provides
    fun providesListRepository(apiGitHub: ApiGitHub):
            ListUsersRepository = ListUsersRepository(apiGitHub)


    @Singleton
    @Provides
    fun providesUserRepository(apiGitHub: ApiGitHub):
            UserDetailsRepository = UserDetailsRepository(apiGitHub)


    @Singleton
    @Provides
    fun providesLoginRepository(apiGitHub: ApiGitHub):
            LoginRepository = LoginRepository(apiGitHub)


    @Singleton
    @Provides
    fun providesMainRepository(): MainRepository = MainRepository()

    /**
     * DataBase Room/Realm
    * */
    //...
}