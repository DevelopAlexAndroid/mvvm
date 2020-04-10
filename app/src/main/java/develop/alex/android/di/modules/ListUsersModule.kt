package develop.alex.android.di.modules

import dagger.Module
import dagger.Provides
import develop.alex.android.ui.adapters.ItemClick
import develop.alex.android.ui.adapters.UsersAdapter
import develop.alex.android.ui.fragments.list_users.ListUsersViewModel
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Module
class ListUsersModule {

    @FragmentScope
    @Provides
    fun provideUsersAdapter(itemClick: ItemClick): UsersAdapter {
        return UsersAdapter(itemClick)
    }

    @FragmentScope
    @Provides
    fun provideAdapterClickListener(listViewModel: ListUsersViewModel): ItemClick {
        return listViewModel
    }
}

