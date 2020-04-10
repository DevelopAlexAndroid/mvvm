package develop.alex.android.ui.fragments.list_users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.repository.ListUsersRepository
import develop.alex.android.providers.Const
import develop.alex.android.ui.adapters.ItemClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListUsersViewModel
@Inject constructor(private val repository: ListUsersRepository) : ViewModel(), ItemClick {

    var users = MutableLiveData<List<ListUserModel>>()

    //mvi sealed class
    fun onCreate() {
        Log.d(Const.APP_TAG, "ListUsersViewModel.onCreate")
        var disp = repository.getUsers()
            .subscribeOn(Schedulers.io())
            .delay(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { res ->
                    users.value = res
                    Log.d(Const.APP_TAG, "getUsers ")
                },
                {
                    Log.d(Const.APP_TAG, it.message.toString())
                }
            )
    }

    override fun click(int: Int) {
        Log.d(Const.APP_TAG, "int $int")
    }

}