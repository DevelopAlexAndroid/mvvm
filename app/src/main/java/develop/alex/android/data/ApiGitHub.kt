package develop.alex.android.data

import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.data.pojo.UserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGitHub {

    @GET("users/{username}")
    fun getUser(@Path("username") name: String): Single<UserModel>

    @GET("users")
    fun getListUsers(): Single<List<ListUserModel>>

    @GET("user")
    //  @Headers(Const.TOKEN_GITHUB)
    fun sigIn(): Single<String>

}