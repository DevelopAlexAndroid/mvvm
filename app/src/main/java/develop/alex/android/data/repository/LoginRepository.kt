package develop.alex.android.data.repository

import android.content.Context
import android.util.Log
import develop.alex.android.providers.Const
import javax.inject.Inject

class LoginRepository @Inject constructor(context: Context) {

    fun test() {
        Log.d(Const.APP_TAG, "123123123123")

    }
}