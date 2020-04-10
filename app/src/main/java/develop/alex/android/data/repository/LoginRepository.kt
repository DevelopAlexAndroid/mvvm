package develop.alex.android.data.repository

import android.util.Log
import develop.alex.android.data.ApiGitHub
import develop.alex.android.providers.Const
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiGitHub: ApiGitHub) {

    fun test() {
        Log.d(Const.APP_TAG, "123123123123")

    }
}