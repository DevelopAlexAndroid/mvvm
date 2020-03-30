package develop.alex.android.ui.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    val isAuthorized = MutableLiveData<Boolean>()

    fun onCreate() {
        Handler().postDelayed({
            isAuthorized.value = false
        }, 2000)
    }

}