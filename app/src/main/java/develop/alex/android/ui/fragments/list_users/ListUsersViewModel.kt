package develop.alex.android.ui.fragments.list_users

import android.util.Log
import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.ListUsersRepository
import develop.alex.android.providers.Const
import javax.inject.Inject

class ListUsersViewModel
@Inject constructor(private val repository: ListUsersRepository) : ViewModel() {

    fun test(text: String) {
        Log.d(Const.APP_TAG, text)
        repository.test()
    }

}