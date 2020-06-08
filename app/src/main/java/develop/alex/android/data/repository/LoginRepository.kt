package develop.alex.android.data.repository

import develop.alex.android.data.ApiGitHub
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiGitHub: ApiGitHub) {

    fun sigIn(): Single<String> {
        return apiGitHub.sigIn()
    }
}