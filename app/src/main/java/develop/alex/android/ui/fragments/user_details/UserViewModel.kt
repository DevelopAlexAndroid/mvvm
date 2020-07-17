package develop.alex.android.ui.fragments.user_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import develop.alex.android.data.pojo.UserModel
import develop.alex.android.data.repository.UserDetailsRepository
import develop.alex.android.providers.Const
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Inject

class UserViewModel
@Inject constructor(
    private val repository: UserDetailsRepository,
    private val sharedPref: SharedPreferencesProvider
) : ViewModel() {

    //Зависимость в фрагмент через VM, можем обрабатывать данные
    val userVMData = MutableLiveData<UserModel>()
    fun getUser(userName: String) {
        Log.d(Const.APP_TAG, "UserViewModel")
        var s = repository.getUser(userName).subscribe({ res ->
            userVMData.value = res
        }, { thr ->
            //fail
        })
    }
    //прямая зависимость из репозитори я в фрагмент - норм, но ограничивает обработку в VM
    val userRepData = repository.data
    fun updateUsersLiveData(userName: String) {
        repository.getUserUsesLiveData(userName)
    }

    //Вариант обработки с Transform LVDT
    private val nameLiveData: MutableLiveData<String> = MutableLiveData()
    fun postName(name: String) = nameLiveData.postValue(name)

    val userLiveData: LiveData<UserModel>
    init {
        userLiveData = Transformations.switchMap(nameLiveData) {
            nameLiveData.value?.let { repository.getUserUsesTransformLiveData(it) }
        }
    }

}