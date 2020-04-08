package develop.alex.android.data.repository

import android.content.Context
import android.util.Log
import develop.alex.android.providers.Const
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Inject

class ListUsersRepository
@Inject constructor(
    context: Context,
    private val sharedPreferencesProvider: SharedPreferencesProvider) {

    fun test() {
        Log.d(Const.APP_TAG, "123123123123")
        sharedPreferencesProvider.saveData()
    }
}