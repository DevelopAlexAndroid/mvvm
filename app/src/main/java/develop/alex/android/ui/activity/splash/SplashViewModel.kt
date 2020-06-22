package develop.alex.android.ui.activity.splash

import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.Const.KEY_AUTHORIZATION
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Inject

class SplashViewModel
@Inject constructor(private val shPr: SharedPreferencesProvider) : ViewModel() {
    //проверка авторизации - для динамической навигации,
    // и чтобы снять всю логику с Main активити
    //проверка токенов и обновление, проверка обновлений приложения

    val isAuthorized = MutableLiveData<Boolean>()

    fun onCreate() {
        Handler().postDelayed({
            val isAuth = shPr.loadPreferencesBoolean(KEY_AUTHORIZATION)
            if (isAuth)
                Log.d(APP_TAG, "user is authorized")//check token, appVersion,
            else
                Log.d(APP_TAG, "user is not authorized")//default navigation

            isAuthorized.value = isAuth
        }, 2000)
    }

    override fun onCleared() {
        super.onCleared()
        //clear
    }

}