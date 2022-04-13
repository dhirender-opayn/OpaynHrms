package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.AddLeaveCategoryJson
import com.example.opaynhrms.model.ClockifyConnectJson
import com.example.opaynhrms.model.ClockifyEntriesJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClockifyWorkRepository(private val baseActivity: Application) {
    var retrofitClient: APIInterface? = null

    fun connectClockify (
        baseActivity: KotlinBaseActivity,
        itemClick: (ClockifyConnectJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.connect_clockify(Utils.AUTHTOKEN)!!
                .enqueue(object : Callback<ClockifyConnectJson> {
                    override fun onResponse(
                        call: Call<ClockifyConnectJson?>,
                        response: Response<ClockifyConnectJson?>
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

                    override fun onFailure(call: Call<ClockifyConnectJson>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                    }
                })
        }


    }

    fun clockify_entries (
        baseActivity: KotlinBaseActivity,
        itemClick: (ClockifyEntriesJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.clockify_entries(Utils.AUTHTOKEN)!!
                .enqueue(object : Callback<ClockifyEntriesJson> {
                    override fun onResponse(
                        call: Call<ClockifyEntriesJson?>,
                        response: Response<ClockifyEntriesJson?>
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

                    override fun onFailure(call: Call<ClockifyEntriesJson>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                    }
                })
        }


    }

}