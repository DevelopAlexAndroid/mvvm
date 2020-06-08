package develop.alex.android.data

import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.pojo.UserModel
import develop.alex.android.providers.Const
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiGitHub {

    @GET("users/DevelopAlexAndroid")
    fun getUser(): Single<UserModel>

    @GET("users")
    fun getListUsers(): Single<List<ListUserModel>>

    @GET("user")
  //  @Headers(Const.TOKEN_GITHUB)
    fun sigIn(): Single<String>

}