package develop.alex.android.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import develop.alex.android.ui.activity.main.MainActivity
import develop.alex.android.ui.activity.main.MainInteractor
import develop.alex.android.ui.activity.main.MainPresenter
import develop.alex.android.ui.fragments.list_users.ListUsersFragment
import develop.alex.android.ui.fragments.login.LoginFragment
import develop.alex.android.ui.fragments.user_details.UserDetailsFragment

@Module
abstract class MainModule {

    @Binds
    abstract fun bindMainPresenter(mainPresenter: MainPresenter): MainInteractor.Presenter
    @Binds
    abstract fun bindsMainView(mainActivity: MainActivity): MainInteractor.View

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListUsersModule::class])
    abstract fun contributeListUsersFragment(): ListUsersFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

}