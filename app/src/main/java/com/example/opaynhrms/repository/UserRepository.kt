package com.example.opaynhrms.repository

import android.app.Application
import android.icu.util.ULocale

import androidx.lifecycle.MutableLiveData
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.*
import com.example.opaynhrms.network.APIInterface
import com.example.opaynhrms.network.RetrofitClient
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class UserRepository(private val baseActivity: Application) {
    var retrofitClient: APIInterface? = null
    private val mutableLiveData = MutableLiveData<ResponseBody>()


    fun commonpostwithtoken(
        baseActivity: KotlinBaseActivity,
        url: String,
        jsonobject: JsonObject,
        itemClick: (ResponseBody) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.commonpostwithtoken(url, Utils.AUTHTOKEN, jsonobject)!!
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }
                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                        // signupmutableLiveData.setValue(null)
                    }
                })
        }


    }

    fun deleteRequestBody(
        baseActivity: KotlinBaseActivity,
        url: String,
        jsonobject: JsonObject,
        itemClick: (ResponseBody) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.deleteRequestBody(url, Utils.AUTHTOKEN, jsonobject)!!
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }
                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                        // signupmutableLiveData.setValue(null)
                    }
                })
        }


    }

    fun getroles(baseActivity: KotlinBaseActivity, itemClick: (RolesJson) -> Unit) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.roles(Utils.AUTHTOKEN)!!.enqueue(object : Callback<RolesJson> {
                override fun onResponse(
                    call: Call<RolesJson?>,
                    response: Response<RolesJson?>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let { itemClick(it) }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<RolesJson?>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                    // signupmutableLiveData.setValue(null)
                }
            })
        }


    }

    fun leavelist(baseActivity: KotlinBaseActivity, itemClick: (LeaveListJson) -> Unit) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.leavelist(Utils.AUTHTOKEN)!!.enqueue(object : Callback<LeaveListJson> {
                override fun onResponse(
                    call: Call<LeaveListJson?>,
                    response: Response<LeaveListJson?>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let { itemClick(it) }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<LeaveListJson?>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                    // signupmutableLiveData.setValue(null)
                }
            })
        }


    }

    fun userslist(baseActivity: KotlinBaseActivity, itemClick: (UserListJson) -> Unit) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.users(Utils.AUTHTOKEN)!!.enqueue(object : Callback<UserListJson> {
                override fun onResponse(
                    call: Call<UserListJson?>,
                    response: Response<UserListJson?>
                ) {
                    baseActivity.stopProgressDialog()
                    when (response.code()) {
                        Keys.RESPONSE_SUCESS -> {
                            response.body()?.let { itemClick(it) }
                        }
                        Keys.ERRORCODE -> {
                            baseActivity.parseError(response)
                        }
                        Keys.UNAUTHoRISE -> {
                            //signupmutableLiveData.setValue(response.body())
                        }
                    }

                }

                override fun onFailure(call: Call<UserListJson?>, t: Throwable) {
                    baseActivity.stopProgressDialog()
                    // signupmutableLiveData.setValue(null)
                }
            })
        }


    }

    fun teamdata(
        baseActivity: KotlinBaseActivity,
        url: String,
        itemClick: (UserDetailJson) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.teamdata(url, Utils.AUTHTOKEN)!!
                .enqueue(object : Callback<UserDetailJson> {
                    override fun onResponse(
                        call: Call<UserDetailJson?>,
                        response: Response<UserDetailJson?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }
                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<UserDetailJson?>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                        // signupmutableLiveData.setValue(null)
                    }
                })
        }


    }

    fun adduser(
        baseActivity: KotlinBaseActivity,
        url: String,
        fields: ArrayList<MultipartBody.Part>,
        itemClick: (LoginJson) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.createuser(Utils.AUTHTOKEN, url, fields)!!
                .enqueue(object : Callback<LoginJson> {
                    override fun onResponse(
                        call: Call<LoginJson?>,
                        response: Response<LoginJson?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }

                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                baseActivity.unauthrizeddialog()
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<LoginJson?>, t: Throwable) {
                        baseActivity.stopProgressDialog()

                    }
                })
        }


    }

    fun addleave(
        baseActivity: KotlinBaseActivity,
        fields: ArrayList<MultipartBody.Part>,
        itemClick: (LoginJson) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )
            retrofitClient?.addleave(Utils.AUTHTOKEN, fields)!!
                .enqueue(object : Callback<LoginJson> {
                    override fun onResponse(
                        call: Call<LoginJson?>,
                        response: Response<LoginJson?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }

                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                baseActivity.unauthrizeddialog()
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<LoginJson?>, t: Throwable) {
                        baseActivity.stopProgressDialog()

                    }
                })
        }


    }





    fun commonpostwithouttoken(
        baseActivity: KotlinBaseActivity,
        url: String,
        itemClick: (ResponseBody) -> Unit
    ) {

        if (!baseActivity.networkcheck.isNetworkAvailable()) {
            baseActivity.nointernershowToast()
        } else {
            baseActivity.startProgressDialog()
            retrofitClient = RetrofitClient.with(this.baseActivity)?.client?.create(
                APIInterface::class.java
            )

            retrofitClient?.commonpostwithouttoken(url )!!
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        baseActivity.stopProgressDialog()
                        when (response.code()) {
                            Keys.RESPONSE_SUCESS -> {
                                response.body()?.let { itemClick(it) }
                            }
                            Keys.ERRORCODE -> {
                                baseActivity.parseError(response)
                            }
                            Keys.UNAUTHoRISE -> {
                                //signupmutableLiveData.setValue(response.body())
                            }
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        baseActivity.stopProgressDialog()
                        // signupmutableLiveData.setValue(null)
                    }
                })
        }


    }


}