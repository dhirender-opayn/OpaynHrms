package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.AddTicketJson
import com.example.opaynhrms.model.TicketListingJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTicketRepository(val baseActivity: Application) {
    var retrofitClient: APIInterface? = null


    fun addTicket(
        baseActivity: KotlinBaseActivity,
        jsonObject: JsonObject,
        itemClick: (AddTicketJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient =
                RetrofitClient.with(this.baseActivity)?.client?.create(APIInterface::class.java)
            retrofitClient?.addTicket(jsonObject)!!.enqueue(object : Callback<AddTicketJson> {
                override fun onResponse(
                    call: Call<AddTicketJson>,
                    response: Response<AddTicketJson>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let {
                                Log.e("deeeeeeeeeeeeeee", it.toString())
                                baseActivity.customSnackBar("Ticket Added Successfully",false)
                                itemClick(it)
                            }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.customSnackBar("Ticket Added Successfully",false)
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            baseActivity.customSnackBar("Ticket Added Successfully",false)
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<AddTicketJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }

            })
        }

    }


    fun ticketListing(
        baseActivity: KotlinBaseActivity,
         token :String,
        itemClick: (TicketListingJson) -> Unit
    ) {
        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            retrofitClient =
                RetrofitClient.with(this.baseActivity)?.client?.create(APIInterface::class.java)
            retrofitClient?.ticket_listing(token)!!.enqueue(object : Callback<TicketListingJson> {
                override fun onResponse(
                    call: Call<TicketListingJson>,
                    response: Response<TicketListingJson>
                ) {
                    baseActivity.startProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let {
                                baseActivity.stopProgressDialog()
                                Log.e("deeeeeeeeeeeeeee", it.toString())

                                itemClick(it)
                            }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.stopProgressDialog()

                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            baseActivity.stopProgressDialog()

                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<TicketListingJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }

            })
        }

    }
}