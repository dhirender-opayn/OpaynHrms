package com.example.opaynhrms.repository

import android.app.Application
import com.bumptech.glide.util.Util
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.LeaveCategoryJson
import com.example.opaynhrms.model.LeaveTypeJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestRepository(private val baseActivity: Application) {
    var retrofitClient: APIInterface? = null


    fun leaveType(baseActivity: KotlinBaseActivity, itemClick: (LeaveTypeJson) -> Unit) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.leaveType(Utils.AUTHTOKEN)!!.enqueue(object : Callback<LeaveTypeJson> {
                override fun onResponse(
                    call: Call<LeaveTypeJson>,
                    response: Response<LeaveTypeJson>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let { itemClick(it) }
                        }
                        Keys.ERRORCODE -> {
//                            baseActivity.parseError(response)
                            baseActivity.customSnackBar(response.toString(), true)
                        }
                        Keys.UNAUTHoRISE -> {
                            baseActivity.customSnackBar(response.toString(), true)
                        }
                    }
                }


                override fun onFailure(call: Call<LeaveTypeJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }

            })
        }
    }

    fun leaveCategory( baseActivity: KotlinBaseActivity, itemClick: (LeaveCategoryJson) -> Unit) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
//            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.leaveCategory(Utils.AUTHTOKEN)!!
                .enqueue(object : Callback<LeaveCategoryJson> {
                    override fun onResponse(
                        call: Call<LeaveCategoryJson>,
                        response: Response<LeaveCategoryJson>
                    ) {
//                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }
                            }
                            Keys.ERRORCODE -> {
//                            baseActivity.parseError(response)
                                baseActivity.customSnackBar(response.toString(), true)
                            }
                            Keys.UNAUTHoRISE -> {
                                baseActivity.customSnackBar(response.toString(), true)
                            }
                        }
                    }


                    override fun onFailure(call: Call<LeaveCategoryJson>, t: Throwable) {
//                        baseActivity.stopProgressDialog()
                    }

                })
        }
    }


}