package develop.alex.android.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import develop.alex.android.data.DataBaseLayer
import develop.alex.android.data.pojo.UserM
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.SharedPreferencesProvider
import io.realm.*
import io.realm.kotlin.addChangeListener
import io.realm.kotlin.where
import javax.inject.Inject

class RegistrationRepository
@Inject constructor(var shprProvider: SharedPreferencesProvider, private var db: DataBaseLayer) {

    init {
        /* val users: RealmResults<UserM> = realm.where(UserM::class.java).findAll()

         users.addChangeListener { results, changeSet ->
             // Query results are updated in real time with fine grained notifications.
             Log.d(APP_TAG, "ChangeListener: ${results[0]?.email} is added")
         }*/

        /* realm.where<UserM>().findAll().addChangeListener { user ->
             Log.d(APP_TAG, "ChangeListener22 ${user[0]?.email}")
         }*/

    }

    private val realm: Realm = db.getRealm()
    val users: UserM? = realm.where(UserM::class.java).findFirst()
    val testLiveData = MutableLiveData<UserM>()


    fun testSaveRealm(userM: UserM) {
        Log.d(APP_TAG, "testSaveRealm")
        db.saveDataInDataBase(userM)

        testLiveData.value = UserM(
            1,
            "name1",
            "1",
            "1",
            "1"
        )
    }

    fun testLoadRealm(): UserM? {
        Log.d(APP_TAG, "testLoadRealm")

        val user = realm.where(UserM::class.java).findFirst()
        Log.d(APP_TAG, "testLoadRealm = ${user?.name}")

        return user
    }

    fun testSaveAndCheckRealmAsync() {
        Log.d(APP_TAG, "testSaveAndCheckRealmAsync")

        val user1 = realm.where(UserM::class.java).findFirst()
        Log.d(APP_TAG, "testSaveAndCheckRealmAsync email = ${user1?.email}")

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(Realm.Transaction
        { bgRealm ->
            Log.d(APP_TAG, "executeTransactionAsync")
            val user: UserM? = bgRealm
                .where(UserM::class.java)
                .equalTo("name", "name")
                .findFirst()

            user?.email = "asd"
        }, Realm.Transaction.OnSuccess {
            // Original queries and Realm objects are automatically updated.
            Log.d(APP_TAG, "$user1") // => 3 the dogs age is updated
        })

    }

}