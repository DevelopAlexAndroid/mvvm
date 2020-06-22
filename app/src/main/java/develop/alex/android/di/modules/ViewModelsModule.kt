package develop.alex.android.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import develop.alex.android.data.repository.MainRepository
import develop.alex.android.di.ViewModelKey
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.ui.activity.main.MainViewModel
import develop.alex.android.ui.activity.splash.SplashViewModel
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

    //ViewModelsActivity
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    //ViewModelsFragment
    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    abstract fun bindListUsersViewModel(listUsersViewModel: ListUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(usersViewModel: UserViewModel): ViewModel

}