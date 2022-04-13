package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.extensions.hideKeyboard
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils.getMultiPart
import com.google.gson.Gson
import com.example.opaynhrms.base.AppViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File
import java.util.ArrayList

class EditProfileViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityEditProfileBinding
    var loginSigupRepository: LoginRepository = LoginRepository(application)

    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    var file: File? = null

    fun setBinder(binder: ActivityEditProfileBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setData()
        settoolbar()

    }
    private fun settoolbar(){
        binder.toolbar.tvtitle.setText(baseActivity.getText(R.string.edit_profile))

    }

      fun setData() {
        binder.tvusername.setText(Home.userModel!!.data.user.name)
        binder.tvemail.setText(Home.userModel!!.data.user.email)
        binder.tvmobile.setText(Home.userModel!!.data.user.mobile)
        if (Home.userModel!!.data.user.profile.isNotNull() && Home.userModel!!.data.user.profile.image.isNotNull()) {
//            Picasso.get().load(Home.userModel!!.data.user.profile.image)
//                .placeholder(R.drawable.userwhite).into(binder.ivprofileimg)
            binder.tvclockify.setText(Home.userModel?.data?.user?.profile?.clockify_key.toString())
            Glide.with(baseActivity).load(Home.userModel!!.data.user.profile.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .into(binder.ivprofileimg)
        }
    }


    fun setfile(file: File) {
        this.file = file
        Glide.with(baseActivity).load(file).diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).into(binder.ivprofileimg)
    }

    private fun validations(): Boolean {
        if (binder.tvusername.text!!.trim().isEmpty()) {
            baseActivity.showtoast(baseActivity.getString(R.string.v_entername))
            return false
        } else if (binder.tvmobile.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_phonenumber))
            return false
        }
        return true
    }

    private fun updateprofile() {
        val fields = ArrayList<MultipartBody.Part>()
        getMultiPart(Keys.name, binder.tvusername.text.toString())?.let { fields.add(it) }
        getMultiPart(Keys.email, binder.tvemail.text.toString())?.let { fields.add(it) }
        getMultiPart(Keys.mobile, binder.tvmobile.text.toString())?.let { fields.add(it) }
        getMultiPart(Keys.id, Home.userModel!!.data.user.id.toString())?.let { fields.add(it) }

//        getMultiPart(Keys.clockify_key, Home.userModel!!.data.user.id.toString())?.let {
//            fields.add(it)
//        }

        getMultiPart(Keys.clockify_key, binder.tvclockify.text.toString())?.let {
            fields.add(it)
        }
        if (file != null) {
            getMultiPart(Keys.image, file!!)?.let { fields.add(it) }
        }
        loginSigupRepository.updateprofile(baseActivity, fields) {
            if (!it.data.isNull()) {
                val gson = Gson()
                val json = gson.toJson(it)
                baseActivity.preferencemanger.saveString(Keys.USERDATA, json)
                Home.userModel = it

                showToast(baseActivity.getString(R.string.profileupdate))
                baseActivity.onBackPressed()

            }

        }
    }

    private fun setclicks() {
        binder.loginbtn.setOnClickListener {
            if (validations()) {
                updateprofile()
            }
        }
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
        binder.llcontainer.setOnClickListener {
            baseActivity.hideKeyboard()
        }

    }

}