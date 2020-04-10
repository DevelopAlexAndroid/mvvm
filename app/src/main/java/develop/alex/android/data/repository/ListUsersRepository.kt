package develop.alex.android.data.repository

import develop.alex.android.data.ApiGitHub
import develop.alex.android.data.pojo.ListUserModel
import io.reactivex.Single
import javax.inject.Inject

class ListUsersRepository @Inject constructor(private val apiGitHub: ApiGitHub) {

    fun getUsers(): Single<List<ListUserModel>> {
        return apiGitHub.getListUsers()
    }
}