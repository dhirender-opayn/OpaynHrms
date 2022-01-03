package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AttendanceListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.repository.RolesJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.Gson
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File

class AddUserViewModel(application: Application) : AppViewModel(application), View.OnClickListener
{
    private lateinit var binder: ActivityAddUserBinding
    private lateinit var mContext: Context
    var userRepository: UserRepository = UserRepository(application)
    lateinit var baseActivity: KotlinBaseActivity
    var roleslist = ArrayList<String>()
    var mainrolelist = ArrayList<RolesJson.Data>()
    val bundle = Bundle()
    var roleid = ""
    var userid = ""
    var file: File? = null
    fun setBinder(binder: ActivityAddUserBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {

            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            binder.tvtitle.text = baseActivity.getString(R.string.edituser)
            binder.singupbtn.text = baseActivity.getString(R.string.update)
            setdata(userdata)
        }
        setclicks()
        getroles()

    }

    private fun getroles() {
        userRepository.getroles(baseActivity) {
            mainrolelist.clear()
            mainrolelist.addAll(it.data)
            it.data.forEach {
                roleslist.add(it.name)
            }
            testtypeadapter()
        }
    }

    private fun setdata(userdata: UserListJson.Data) {
        userid=userdata.id.toString()
        binder.tvName.setText(userdata.name)
        binder.tvEmail.setText(userdata.email)
        binder.tvMobile.setText(userdata.mobile)
        roleid=userdata.roles[0].id.toString()
        binder.passwordWrap.gone()
        binder.leavetypewrap.gone()
        binder.tvEmail.isEnabled = false
    }

    private fun testtypeadapter() {
        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, roleslist)
        binder.autoRole.setAdapter(aa)
        binder.autoRole.setFocusable(false)
        binder.autoRole.setFocusableInTouchMode(false)
        binder.autoRole.setOnClickListener(this)
        binder.autoRole.setKeyListener(null)
        binder.autoRole.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            roleid = mainrolelist[i].id.toString()
        })
    }

    fun setfile(file: File) {
        this.file = file
        Glide.with(baseActivity).load(file).diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).into(binder.ivprofileimg)
    }

    private fun validations(): Boolean
    {
        if (binder.tvName.text!!.trim().isEmpty())
        {
            baseActivity.showtoast(baseActivity.getString(R.string.v_entername))
            return false
        }
        else if (binder.tvEmail.text.toString().isEmpty())
        {
            showToast(mContext.getString(R.string.v_email))
            return false
        }
        else if (!isEmailValid(binder.tvEmail.text!!.trim().toString())) {
            showToast(mContext.getString(R.string.v_validemail))
            return false
        } else if (binder.tvMobile.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_phonenumber))
            return false
        }
        if (!binder.tvtitle.text.toString().equals(baseActivity.getString(R.string.edituser)))
        {
            if (binder.tvnewpassord.text.toString().isEmpty()) {
                showToast(mContext.getString(R.string.v_password))
                return false
                if (roleid.isEmpty()) {
                    showToast(mContext.getString(R.string.v_role))
                    return false
                }
            }


        }
        return true
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener(this)
        binder.singupbtn.setOnClickListener(this)
        binder.clsingupcontainer.setOnClickListener(this)

    }

    private fun createUser() {
        var  url=""
        val fields = java.util.ArrayList<MultipartBody.Part>()
        Utils.getMultiPart(Keys.name, binder.tvName.text.toString())?.let { fields.add(it) }
        Utils.getMultiPart(Keys.email, binder.tvEmail.text.toString())?.let { fields.add(it) }
        Utils.getMultiPart(Keys.mobile, binder.tvMobile.text.toString())?.let { fields.add(it) }
        // create user
        if (!binder.tvtitle.text.toString().equals(baseActivity.getString(R.string.edituser)))
        {
            url=Keys.DELTEUSER
            Utils.getMultiPart(Keys.password, binder.tvnewpassord.text.toString())
                ?.let { fields.add(it) }
            Utils.getMultiPart(Keys.role_id, roleid)?.let { fields.add(it) }
        }
        else
        {
            url=Keys.UPDATEUSER
            Utils.getMultiPart(Keys.id, userid)?.let { fields.add(it) }
        }

        Utils.getMultiPart(Keys.clockify_key, "")?.let { fields.add(it) }
        if (file != null) {
            Utils.getMultiPart(Keys.image, file!!)?.let { fields.add(it) }
        }
        userRepository.adduser(baseActivity, url,fields) {
            if (!it.data.isNull()) {
                if (userid.isEmpty())
                {
                    baseActivity.showtoast("User added successfully")
                }
                else{
                    baseActivity.showtoast("User update successfully")
                }
                baseActivity.finish()
            }

        }
    }

    override fun onClick(p0: View?)
    {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.auto_role -> {
                baseActivity.showDropDown(binder.autoRole)
            }
            R.id.singupbtn -> {
                if (validations()) {
                    createUser()
                }
            }
            R.id.clsingupcontainer -> {
                baseActivity.hideKeyboard()
            }
        }

    }
}