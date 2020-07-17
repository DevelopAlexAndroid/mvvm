package develop.alex.android.ui.fragments.registration

import android.util.Log
import androidx.lifecycle.*
import develop.alex.android.data.pojo.UserM
import develop.alex.android.data.repository.RegistrationRepository
import develop.alex.android.providers.Const
import develop.alex.android.providers.Const.APP_TAG
import io.realm.RealmChangeListener
import io.realm.RealmObjectChangeListener
import io.realm.kotlin.addChangeListener
import javax.inject.Inject

class RegistrationViewModel
@Inject constructor(
    var repository: RegistrationRepository
) : ViewModel() {

    init {
        createObserverRepositoryRealm()
    }

    val userRealmListenerLiveData = MutableLiveData<UserM>()
    private fun createObserverRepositoryRealm() {
        //вызыввается дважды
        repository.users?.addChangeListener<UserM> { t ->
            Log.d(APP_TAG, "createObserverRepositoryRealm: ${t.email}")
            userRealmListenerLiveData.value = t
        }
    }

    fun test() {
        repository.testSaveRealm(
            UserM(
                89615290903,
                "name",
                "s",
                "123",
                "pass"
            )
        )

       // repository.testLoadRealm()

     //   repository.testSaveAndCheckRealmAsync()
    }

}