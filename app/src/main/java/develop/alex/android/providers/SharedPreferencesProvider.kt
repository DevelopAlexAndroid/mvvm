package develop.alex.android.providers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class SharedPreferencesProvider
@Inject constructor(context: Context) {

    init {
    }

    private lateinit var sharedPreferences: SharedPreferences

    fun saveData(){
        Log.d(Const.APP_TAG, "saveData")
    }
    fun loadData(){
        Log.d(Const.APP_TAG, "loadData")
    }

}