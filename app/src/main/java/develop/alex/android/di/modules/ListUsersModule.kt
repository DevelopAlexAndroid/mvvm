package develop.alex.android.di.modules

import dagger.Module
import dagger.Provides
import develop.alex.android.ui.adapters.ListenerAdapter
import develop.alex.android.ui.adapters.UsersAdapter
import develop.alex.android.ui.fragments.list_users.ListUsersFragment
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Module
class ListUsersModule {

    @FragmentScope
    @Provides
    fun provideUsersAdapter(listenerAdapter: ListenerAdapter): UsersAdapter {
        return UsersAdapter(listenerAdapter)
    }

    @FragmentScope
    @Provides
    fun provideAdapterClickListener(listUsersFragment: ListUsersFragment): ListenerAdapter {
        return listUsersFragment
    }
}

