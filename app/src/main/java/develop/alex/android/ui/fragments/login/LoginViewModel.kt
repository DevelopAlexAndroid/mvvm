package develop.alex.android.ui.fragments.login

import android.util.Log
import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.LoginRepository
import develop.alex.android.providers.Const
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val cmpDis = CompositeDisposable()

    fun sigIn() {
        cmpDis.add(
            repository.sigIn().subscribe({
                Log.d(Const.APP_TAG, "LoginViewModel.Success")
            }, {
                Log.d(Const.APP_TAG, "LoginViewModel.Fail = ${it.message}")
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Const.APP_TAG, "LoginViewModel.onCleared")
        cmpDis.clear()
    }

}