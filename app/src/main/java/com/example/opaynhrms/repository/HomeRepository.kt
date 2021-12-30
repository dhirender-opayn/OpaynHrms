package com.example.opaynhrms.repository
 import android.app.Application

import androidx.lifecycle.MutableLiveData
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.model.LoginJson
 import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
 import com.example.opaynhrms.ui.Home
 import com.example.opaynhrms.utils.Keys
 import com.example.opaynhrms.utils.Utils
 import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepository(private val baseActivity: Application)
{
    var retrofitClient: APIInterface? = null
    private val mutableLiveData = MutableLiveData<ResponseBody>()
    fun commonpost(baseActivity: KotlinBaseActivity, url:String, jsonobject: JsonObject, itemClick: (ResponseBody) -> Unit)
    {

        if (!baseActivity.networkcheck.isNetworkAvailable())
        {
            baseActivity.nointernershowToast()
        }
        else{
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.commonpost(url,jsonobject)!!.enqueue(object : Callback<ResponseBody>
            {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    baseActivity.stopProgressDialog()
                    when(response.code())
                    {
                        Keys.RESPONSE_SUCESS->{
                            response.body()?.let { itemClick(it) }
                         }
                        Keys.ERRORCODE->{
                             baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE->{
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable)
                {
                    baseActivity.stopProgressDialog()
                   // signupmutableLiveData.setValue(null)
                }
            })
        }




    }
    fun commonpostwithtoken(baseActivity: KotlinBaseActivity, url:String, jsonobject: JsonObject, itemClick: (ResponseBody) -> Unit)
    {

        if (!baseActivity.networkcheck.isNetworkAvailable())
        {
            baseActivity.nointernershowToast()
        }
        else{
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.commonpostwithtoken(url,Utils.AUTHTOKEN,jsonobject)!!.enqueue(object : Callback<ResponseBody>
            {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    baseActivity.stopProgressDialog()
                    when(response.code())
                    {
                        Keys.RESPONSE_SUCESS->{
                            response.body()?.let { itemClick(it) }
                         }
                        Keys.ERRORCODE->{
                             baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE->{
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable)
                {
                    baseActivity.stopProgressDialog()
                   // signupmutableLiveData.setValue(null)
                }
            })
        }




    }


}