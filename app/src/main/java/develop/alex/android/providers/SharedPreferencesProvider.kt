package develop.alex.android.providers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import develop.alex.android.providers.Const.APP_TAG
import javax.inject.Inject

class SharedPreferencesProvider
@Inject constructor(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Const.SP_SETTINGS, MODE_PRIVATE)

    fun savePreferencesBoolean(key: String, value: Boolean) {
        Log.d(APP_TAG, "SharedPreferences - saveData")
        sharedPreferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun loadPreferencesBoolean(key: String): Boolean {
        Log.d(APP_TAG, "SharedPreferences - loadData")
        return sharedPreferences.getBoolean(key, false)
    }

}