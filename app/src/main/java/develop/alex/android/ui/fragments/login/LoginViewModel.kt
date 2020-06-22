package develop.alex.android.ui.fragments.login

import android.util.Log
import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.LoginRepository
import develop.alex.android.providers.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun sigIn() {
        compositeDisposable.add(repository.sigIn()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(Const.APP_TAG, "LoginViewModel.Success")
                }, {
                    Log.d(Const.APP_TAG, "LoginViewModel.Fail = ${it.message}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}