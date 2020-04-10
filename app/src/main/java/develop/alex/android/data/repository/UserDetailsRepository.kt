package develop.alex.android.data.repository

import android.util.Log
import develop.alex.android.data.ApiGitHub
import develop.alex.android.data.pojo.UserModel
import develop.alex.android.providers.Const
import io.reactivex.Single
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(private val apiGitHub: ApiGitHub) {

    fun getUser(): Single<UserModel> {
        Log.d(Const.APP_TAG, "UserDetailsRepository.getUser")
        return apiGitHub.getUser()
    }
}