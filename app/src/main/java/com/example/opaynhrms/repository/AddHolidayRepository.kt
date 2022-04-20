package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.AddHolidayJson
import com.example.opaynhrms.model.HolidayListingJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddHolidayRepository(val baseActivity: Application) {
    var retrofitClient: APIInterface? = null

    fun addHoliday(
        baseActivity: KotlinBaseActivity,
        url: String,
        jsonObject: JsonObject,
        itemClick: (AddHolidayJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient =
                RetrofitClient.with(this.baseActivity)?.client?.create(APIInterface::class.java)
            retrofitClient?.addHoliday( Utils.AUTHTOKEN,url, jsonObject)!!.enqueue(object : Callback<AddHolidayJson> {
                override fun onResponse(
                    call: Call<AddHolidayJson>,
                    response: Response<AddHolidayJson>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let {
                                Log.e("deeeeeeeeeeeeeee", it.toString())
                                baseActivity.customSnackBar("Holiday Added Successfully",false)
                                itemClick(it)
                            }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.customSnackBar("Holiday Added Successfully",false)
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            baseActivity.customSnackBar("Holiday Added Successfully",false)
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<AddHolidayJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }

            })
        }

    }




    fun holidayListing(
        baseActivity: KotlinBaseActivity,
         itemClick: (HolidayListingJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient =
                RetrofitClient.with(this.baseActivity)?.client?.create(APIInterface::class.java)
            retrofitClient?.holidayListing( Utils.AUTHTOKEN)!!.enqueue(object : Callback<HolidayListingJson> {
                override fun onResponse(
                    call: Call<HolidayListingJson>,
                    response: Response<HolidayListingJson>
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

                override fun onFailure(call: Call<HolidayListingJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }

            })
        }

    }
}