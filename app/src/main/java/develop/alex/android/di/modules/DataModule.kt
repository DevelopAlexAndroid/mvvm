package develop.alex.android.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import develop.alex.android.data.repository.ListUsersRepository
import develop.alex.android.data.repository.LoginRepository
import develop.alex.android.data.repository.UserDetailsRepository
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Singleton

@Module
class DataModule {

    /**
     * Repository
    * */
    @Singleton
    @Provides
    fun providesListRepository(
        context: Context,
        sharedPreferencesProvider: SharedPreferencesProvider): ListUsersRepository {
        return ListUsersRepository(context, sharedPreferencesProvider)
    }

    @Singleton
    @Provides
    fun providesUserRepository(context: Context): UserDetailsRepository {
        return UserDetailsRepository(context)
    }

    @Singleton
    @Provides
    fun providesRepository(context: Context): LoginRepository {
        return LoginRepository(context)
    }

    /**
     * DataBase Room
    * */
    //...
}