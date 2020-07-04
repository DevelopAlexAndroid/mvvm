package develop.alex.android.data

import android.content.Context
import io.realm.Realm
import javax.inject.Inject

class DataBaseLayer
@Inject constructor(context: Context){

    private var realm: Realm

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    fun getRealm(): Realm = realm


}