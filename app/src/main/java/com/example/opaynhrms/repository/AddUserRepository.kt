package com.example.opaynhrms.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.AddLeaveCategoryJson
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonObject
 import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserRepository (private val baseActivity: Application)
{

    var retrofitClient: APIInterface? = null

    fun addleavecategory(baseActivity: KotlinBaseActivity, url:String, jsonobject: JsonObject, itemClick: (AddLeaveCategoryJson) -> Unit)
    {

        if (!baseActivity.networkcheck.isNetworkAvailable())
        {
            baseActivity.nointernershowToast()
        }
        else{
          //  baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.add_leave_detail(Utils.AUTHTOKEN, jsonobject)!!.enqueue(object : Callback<AddLeaveCategoryJson>
            {
                override fun onResponse(
                    call: Call<AddLeaveCategoryJson?>,
                    response: Response<AddLeaveCategoryJson?>
                ) {
                    baseActivity.stopProgressDialog()
                    when(response.code())
                    {
                        Keys.RESPONSE_SUCESS->{
                            response.body()?.let {
                                Log.e("deeeeeeeeeeeeeee",it.toString())
                                itemClick(it)
                              }
                        }
                        Keys.ERRORCODE->{
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE->{
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }
                override fun onFailure(call: Call<AddLeaveCategoryJson>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                }
            })
        }




    }

}