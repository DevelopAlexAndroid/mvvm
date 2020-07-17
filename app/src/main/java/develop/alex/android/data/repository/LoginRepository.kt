package develop.alex.android.data.repository

import develop.alex.android.data.ApiGitHub
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginRepository
@Inject constructor(
    private val apiGitHub: ApiGitHub
) {

    fun sigIn(): Single<String> {
        return apiGitHub.sigIn()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}