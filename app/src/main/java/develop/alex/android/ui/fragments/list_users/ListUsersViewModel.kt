package develop.alex.android.ui.fragments.list_users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import develop.alex.android.data.ListUsersParameters
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

    init {
        //загрузка данных при старте фрагмента только 1 раз
    }

    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        Log.d(APP_TAG, "ListUsersViewModel.onCleared")
        compositeDisposable.clear()
        clearLazyMap()
    }

    fun clearLazyMap() {
        (loadUsersLazyMapOnlyOnceAndGiveParams as MutableMap).clear()
    }

    //load in lifeCycle
    var users = MutableLiveData<List<ListUserModel>>()
    fun setupLoadDataInLifeCycleMethod() {
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

    //mvi sealed class
    fun loadUsersLazyMapWithMvi(parameter: ListUsersParameters): MutableLiveData<ListUsersState> {
        Log.d(APP_TAG, "loadUsersLazyMapWithMvi ")
        return loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi.getValue(parameter)
    }

    private val loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi:
            Map<ListUsersParameters, MutableLiveData<ListUsersState>> =
        this.lazyMap { parameter ->
            Log.d(APP_TAG, "parameter = ${parameter.id}")
            val listUsersLazyMap = MutableLiveData<ListUsersState>()
            //Loading
            listUsersLazyMap.value = ListUsersState.Loading
            compositeDisposable.add(getUsers().subscribe(
                { res ->
                    Log.d(
                        APP_TAG,
                        "loadUsersLazyMapOnlyOnceAndGiveParamsWithMvi with parameter = $parameter"
                    )
                    //Success
                    listUsersLazyMap.value = ListUsersState.ShowData(res)
                },
                {
                    //Error
                    listUsersLazyMap.value = ListUsersState.ErrorNetwork
                    Log.d(APP_TAG, it.message.toString())
                }
            ))
            return@lazyMap listUsersLazyMap
        }

    private fun getUsers(): Single<List<ListUserModel>> {
        return repository.getUsers()
            .subscribeOn(Schedulers.io())
            .delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
    }
}











