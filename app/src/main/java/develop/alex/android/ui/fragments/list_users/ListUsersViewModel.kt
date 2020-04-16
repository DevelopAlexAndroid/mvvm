package develop.alex.android.ui.fragments.list_users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.repository.ListUsersRepository
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.lazyMap
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListUsersViewModel
@Inject constructor(private val repository: ListUsersRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        Log.d(APP_TAG, "ListUsersViewModel.onCleared")
        compositeDisposable.clear()
    }
    fun clearLazyMap() { (loadUsersLazyMapOnlyOnceAndGiveParams as MutableMap).clear() }

    //load in lifeCycle
    var users = MutableLiveData<List<ListUserModel>>()
    fun onCreate() {
        Log.d(APP_TAG, "ListUsersViewModel.onCreate")
        compositeDisposable.add(getUsers().subscribe(
            { res ->
                Log.d(APP_TAG, "onCreate.getUsers ")
                users.value = res
            },
            {
                Log.d(APP_TAG, it.message.toString())
            }
        ))
    }

    //load in lazy val
    fun loadUsersLazy(): MutableLiveData<List<ListUserModel>> = loadUsersLazyOnlyOnce
    private val loadUsersLazyOnlyOnce by lazy {
        val listUsers = MutableLiveData<List<ListUserModel>>()
        compositeDisposable.add(getUsers().subscribe(
                { res ->
                    Log.d(APP_TAG, "loadUsersOnlyOnce.getUsers ")
                    listUsers.value = res
                },
                {
                    Log.d(APP_TAG, it.message.toString())
                }
            ))
        return@lazy listUsers
    }

    //load in lazyMap val
    fun loadUsersLazyMap(parameter: String): MutableLiveData<List<ListUserModel>> {
        Log.d(APP_TAG, "loadUsersLazyMap ")
        return loadUsersLazyMapOnlyOnceAndGiveParams.getValue(parameter)
    }
    private val loadUsersLazyMapOnlyOnceAndGiveParams: Map<String, MutableLiveData<List<ListUserModel>>> =
        this.lazyMap { parameter ->
            val listUsersLazyMap = MutableLiveData<List<ListUserModel>>()
            compositeDisposable.add(getUsers().subscribe(
                    { res ->
                        Log.d(APP_TAG, "loadUsersOnlyOnce.getUsers with parameter = $parameter")
                        listUsersLazyMap.value = res
                    },
                    {
                        Log.d(APP_TAG, it.message.toString())
                    }
                ))
            return@lazyMap listUsersLazyMap
        }

    /*mvi sealed class
    * если первая загрузка, то грузим данные
    * 2 - проверяем данные в sealed class
    * если ошибка, то пытаемся обновить
    * если есть, то отображаем их
    * */
    fun loadUsersLazyMapWithMvi(parameter: String): MutableLiveData<ListUsersState> {
        Log.d(APP_TAG, "loadUsersLazyMapWithMvi ")
        return loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi.getValue(parameter)
    }
    private val loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi: Map<String, MutableLiveData<ListUsersState>> =
        this.lazyMap { parameter ->
            val listUsersLazyMap = MutableLiveData<ListUsersState>()
            listUsersLazyMap.value = ListUsersState.Loading
            compositeDisposable.add(getUsers().subscribe(
                { res ->
                    Log.d(APP_TAG, "loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi with parameter = $parameter")
                    listUsersLazyMap.value = ListUsersState.ShowData(res)
                },
                {
                    listUsersLazyMap.value = ListUsersState.ErrorNetwork
                    Log.d(APP_TAG, it.message.toString())
                }
            ))
            return@lazyMap listUsersLazyMap
        }

    private fun getUsers(): Single<List<ListUserModel>>{
        Log.d(APP_TAG, "ListUsersViewModel.onCreate")
        return repository.getUsers()
            .subscribeOn(Schedulers.io())
            .delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
    }
}











