package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.UserLeaveDetailJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaveManagementRepository(private val baseActivity: Application) {

    var retrofitClient: APIInterface? = null

    fun getLeaveByUser(
        baseActivity: KotlinBaseActivity,
        url: String,

         itemClick: (UserLeaveDetailJson) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.user_leave_detail(Utils.AUTHTOKEN)!!.enqueue(object :
                Callback<UserLeaveDetailJson> {
                override fun onResponse(
                    call: Call<UserLeaveDetailJson?>,
                    response: Response<UserLeaveDetailJson?>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let {
                                Log.e("deeeeeeeeeeeeeee", it.toString())
                                itemClick(it)
                            }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }


                override fun onFailure(call: Call<UserLeaveDetailJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }
            })
        }


    }


}