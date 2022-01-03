package com.example.opaynhrms.network

 import com.example.opaynhrms.model.AttandanceListJson
 import com.example.opaynhrms.model.LeaveListJson
 import com.example.opaynhrms.model.LoginJson
 import com.example.opaynhrms.model.UserListJson
 import com.example.opaynhrms.repository.RolesJson
 import com.google.gson.JsonObject
 import okhttp3.MultipartBody

 import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
 import java.util.ArrayList


interface APIInterface
{
    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body jsonObject: JsonObject?): Call<LoginJson>?
    @Headers("Accept: application/json")
    @POST("")
    fun commonpost(@Url url:String,@Body jsonObject: JsonObject?): Call<ResponseBody>?
    @Headers("Accept: application/json")
    @POST("")
    fun commonpostwithtoken(@Url url:String,@Header("Authorization") token: String,@Body jsonObject: JsonObject?): Call<ResponseBody>
    @Headers("Accept: application/json")

    @Multipart
    @POST("user/update")
    fun updateuser(
        @Header("Authorization") token: String,
        @Part fields: ArrayList<MultipartBody.Part>): Call<LoginJson>

    @Headers("Accept: application/json")
    @Multipart
    @POST("user")
    fun createuser(
        @Header("Authorization") token: String,
        @Part fields: ArrayList<MultipartBody.Part>): Call<LoginJson>
    @Headers("Accept: application/json")
    @Multipart
    @POST("leave")
    fun addleave(
        @Header("Authorization") token: String,
        @Part fields: ArrayList<MultipartBody.Part>): Call<LoginJson>



    @Headers("Accept: application/json")
    @GET("roles")
    fun roles(@Header("Authorization") token: String): Call<RolesJson>

    @Headers("Accept: application/json")
    @GET("leave/listing")
    fun leavelist(@Header("Authorization") token: String): Call<LeaveListJson>

    @Headers("Accept: application/json")
    @GET("users")
    fun users(@Header("Authorization") token: String): Call<UserListJson>

    @Headers("Accept: application/json")
    @GET("")
    fun attdancelist(@Header("Authorization") token: String,@Url url:String ): Call<AttandanceListJson>

}