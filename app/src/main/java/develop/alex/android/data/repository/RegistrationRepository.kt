package develop.alex.android.data.repository

import android.util.Log
import develop.alex.android.App
import develop.alex.android.data.DataBaseLayer
import develop.alex.android.data.pojo.UserM
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.SharedPreferencesProvider
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class RegistrationRepository
@Inject constructor(var shprProvider: SharedPreferencesProvider, dbLayer: DataBaseLayer) {

    private val realm: Realm =dbLayer.getRealm()

    init {
        val users: RealmResults<UserM> = realm.where(UserM::class.java).findAll()

        users.addChangeListener { results, changeSet ->
            // Query results are updated in real time with fine grained notifications.
            Log.d(APP_TAG, "addChangeListener: ${changeSet.insertions} is added")
            changeSet.insertions // => [0] is added.
        }
    }

    fun testSaveRealm(userM: UserM) {
        Log.d(APP_TAG, "testSaveRealm")

        realm.beginTransaction()
        realm.copyToRealmOrUpdate(userM)
        realm.commitTransaction()

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