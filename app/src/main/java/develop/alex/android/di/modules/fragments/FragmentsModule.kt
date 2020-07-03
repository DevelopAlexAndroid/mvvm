package develop.alex.android.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import develop.alex.android.di.modules.fragments.FragmentScope
import develop.alex.android.di.modules.fragments.ListUsersModule
import develop.alex.android.ui.fragments.list_users.ListUsersFragment
import develop.alex.android.ui.fragments.login.LoginFragment
import develop.alex.android.ui.fragments.registration.RegistrationFragment
import develop.alex.android.ui.fragments.user_details.UserDetailsFragment

@Module
abstract class FragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListUsersModule::class])
    abstract fun contributeListUsersFragment(): ListUsersFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment

}