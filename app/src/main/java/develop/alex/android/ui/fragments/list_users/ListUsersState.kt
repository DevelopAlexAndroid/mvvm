package develop.alex.android.ui.fragments.list_users

import develop.alex.android.data.pojo.ListUserModel

sealed class ListUsersState {

    object Loading : ListUsersState()

    data class ShowData(val data: List<ListUserModel>) : ListUsersState()

    object ErrorNetwork : ListUsersState()

}