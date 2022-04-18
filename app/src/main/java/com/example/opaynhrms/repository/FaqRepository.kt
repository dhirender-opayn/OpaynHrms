package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.FAQJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FaqRepository(val baseActivity: Application) {
    var retrofitClient :APIInterface? = null
    fun faq(baseActivity: KotlinBaseActivity , itemClick: (FAQJson)-> Unit){
            if (!baseActivity.networkcheck.isNetworkAvailable()){
                baseActivity.nointernershowToast()
                return
            }
            else
            {
                baseActivity.startProgressDialog()
                retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(APIInterface::class.java)
                retrofitClient?.faq()!!.enqueue(object : Callback<FAQJson>{
                    override fun onResponse(call: Call<FAQJson>, response: Response<FAQJson>) {
                         when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let {
                                    baseActivity.stopProgressDialog()
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

                    override fun onFailure(call: Call<FAQJson>, t: Throwable) {

                    }

                })

            }

    }

}