package develop.alex.android.ui.fragments.user_details

import android.util.Log
import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.UserDetailsRepository
import develop.alex.android.providers.Const
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Inject

class UserViewModel
@Inject constructor(
    private val repository: UserDetailsRepository,
    private val sharedPref: SharedPreferencesProvider
) : ViewModel() {

    fun test() {
        Log.d(Const.APP_TAG, "UserViewModel")
        repository.getUser()
        sharedPref.saveData()
    }

}