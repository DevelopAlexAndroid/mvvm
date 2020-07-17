package develop.alex.android.data.pojo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserM(
    @PrimaryKey
    var number: Long = 0,
    var name: String = "",
    var surName: String = "",
    var email: String = "",
    var password: String = ""
): RealmObject() //realm doesn't support data class
