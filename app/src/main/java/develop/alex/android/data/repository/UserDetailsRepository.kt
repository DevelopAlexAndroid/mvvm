package develop.alex.android.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import develop.alex.android.data.ApiGitHub
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.pojo.UserModel
import develop.alex.android.providers.Const.APP_TAG
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(private val apiGitHub: ApiGitHub) {

    fun getUser(username: String): Single<UserModel> {
        return apiGitHub
            .getUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    val data = MutableLiveData<UserModel>()
    fun getUserUsesLiveData(username: String) {
        val s = apiGitHub
            .getUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                data.value = res
            }, { tr ->
                Log.d(APP_TAG, tr.toString())
            })
    }

    fun getUserUsesTransformLiveData(name: String): LiveData<UserModel> {
        val ld = MutableLiveData<UserModel>()
        val s = apiGitHub
            .getUser(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                ld.value = res
            }, { tr ->
                Log.d(APP_TAG, tr.toString())
            })
        return ld
    }

}