package develop.alex.android.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import develop.alex.android.ui.fragments.list_users.ListUsersFragment
import develop.alex.android.ui.fragments.login.LoginFragment
import develop.alex.android.ui.fragments.user_details.UserDetailsFragment

@Module
abstract class MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListUsersModule::class])
    abstract fun contributeListUsersFragment(): ListUsersFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

}