package develop.alex.android.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import develop.alex.android.di.ViewModelKey
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.ui.fragments.list_users.ListUsersViewModel
import develop.alex.android.ui.fragments.login.LoginViewModel
import develop.alex.android.ui.fragments.user_details.UserViewModel

@Module
abstract class ViewModelsModule {

    /**
     * Создание фабрики производтсва ViewModel
     * */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //ViewModel for ListUsersFragment
    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    abstract fun bindListUsersViewModel(listUsersViewModel: ListUsersViewModel): ViewModel

    //ViewModel for LoginFragment
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    //ViewModel for UserDetailsFragment
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(usersViewModel: UserViewModel): ViewModel

}