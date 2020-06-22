package develop.alex.android.ui.activity.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.MainRepository
import develop.alex.android.providers.Const
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.Const.KEY_AUTHORIZATION
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private var mainRepository: MainRepository,
    private var shPr: SharedPreferencesProvider
) : ViewModel() {

    val navigationData = MutableLiveData<Boolean>()

    fun onCreate() {
        Log.d(APP_TAG, "MainActivity Created")
        navigationData.value = shPr.loadPreferencesBoolean(KEY_AUTHORIZATION)
    }

}