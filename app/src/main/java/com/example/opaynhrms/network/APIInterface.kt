package com.example.opaynhrms.network

import com.example.opaynhrms.model.*
import com.example.opaynhrms.repository.RolesJson
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList


interface APIInterface {
    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body jsonObject: JsonObject?): Call<LoginJson>?

    @Headers("Accept: application/json")
    @POST("")
    fun commonpost(@Url url: String, @Body jsonObject: JsonObject?): Call<ResponseBody>?

    @Headers("Accept: application/json")
    @POST("")
    fun commonpostwithtoken(
        @Url url: String,
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject?
    ): Call<ResponseBody>

    @Headers("Accept: application/json")
    @POST("")
    fun checkincheckout(
        @Url url: String,
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject?
    ): Call<CheckInCheckOutJson>

    @Headers("Accept: application/json")
    @Multipart
    @POST("user/update")
    fun updateuser(
        @Header("Authorization") token: String,
        @Part fields: ArrayList<MultipartBody.Part>
    ): Call<LoginJson>

    @Headers("Accept: application/json")
    @Multipart
    @POST("")
    fun createuser(
        @Header("Authorization") token: String,
        @Url url: String,
        @Part fields: ArrayList<MultipartBody.Part>,
//        @Part("items[]") items:List<String>
     ): Call<LoginJson>

    @Headers("Accept: application/json")
    @Multipart
    @POST("leave")
    fun addleave(
        @Header("Authorization") token: String,
        @Part fields: ArrayList<MultipartBody.Part>
    ): Call<LoginJson>

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
    @GET()
    fun teamdata(@Url url: String, @Header("Authorization") token: String): Call<UserDetailJson>

    @Headers("Accept: application/json")
    @GET("")
    fun attdancelist(
        @Header("Authorization") token: String,
        @Url url: String
    ): Call<AttandanceListJson>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", hasBody = true)
    fun deleteRequestBody(
        @Url url: String,
        @Header("Authorization") token: String,
        @Body map: JsonObject
    ): Call<ResponseBody>


    @Headers("Accept: application/json")
    @GET("leave-types")
    fun leaveType(@Header("Authorization") token: String): Call<LeaveTypeJson>



    @Headers("Accept: application/json")
    @GET("leave-categories")
    fun leaveCategory(@Header("Authorization") token: String): Call<LeaveCategoryJson>

    @Headers("Accept: application/json")
    @GET("")
    fun leaveCategory2(@Header("Authorization") token: String,@Url url: String): Call<LeaveCategoryJson>



    @Headers("Accept: application/json")
    @GET("")
    fun commonpostwithouttoken(
        @Url url: String,
    ): Call<ResponseBody>

    @Headers("Accept: application/json")
    @POST("add-leave-details")
    fun add_leave_detail(
        @Header("Authorization") token: String,
        @Body jsonObject: JSONObject?
    ): Call<AddLeaveCategoryJson>?


    @Headers("Accept: application/json")
    @GET("leave-details ")
    fun user_leave_detail(@Header("Authorization") token: String): Call<UserLeaveDetailJson>?

    @Headers("Accept: application/json")
    @POST("connect-clockify")
    fun connect_clockify(@Header("Authorization") token: String): Call<ClockifyConnectJson>?

    @Headers("Accept: application/json")
    @GET("clockify-entries")
    fun clockify_entries(@Header("Authorization") token: String): Call<ClockifyEntriesJson>?




    @Headers("Accept: application/json")
    @POST("ticket")
    fun addTicket(
        @Body jsonObject: JsonObject?
    ): Call<AddTicketJson>

    @Headers("Accept: application/json")
    @GET("tickets")
    fun ticket_listing(@Header("Authorization") token: String): Call<TicketListingJson>?



    @Headers("Accept: application/json")
    @GET("")
    fun user_category_by_id( @Header("Authorization") token: String, @Url url: String):Call<UserLeaveDetailJson>

    @Headers("Accept: application/json")
    @GET("faqs")
    fun faq():Call<FAQJson>



//
//    @Headers("Accept: application/json")
//    @GET("leave-details")
//    fun user_leave_detail( @Header("Authorization") token: String ): Call<UserLeaveDetailJson>?

//  User leave detail
//    @Headers("Accept: application/json")
//    @GET("user-leave-details")
//    fun user_leave_detail( @Header("Authorization") token: String ): Call<UserLeaveDetailJson>?

}