package develop.alex.android.data

import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.pojo.UserModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiGitHub {

    @GET("users/DevelopAlexAndroid")
    fun getUser(): Single<UserModel>

    @GET("users")
    fun getListUsers(): Single<List<ListUserModel>>

}