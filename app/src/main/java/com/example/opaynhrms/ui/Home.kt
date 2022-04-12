package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.fragment.HomeFragement
import com.example.opaynhrms.model.LeaveCategoryJson
import com.example.opaynhrms.model.LeaveTypeJson
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.repository.RequestRepository
import com.example.opaynhrms.viewmodel.HomeViewModel
import com.google.gson.Gson

class Home : KotlinBaseActivity(R.id.container) {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        navigateToFragment(HomeFragement(baseActivity = this), bundle, false)
        viewmodel.setBinder(binding, this)

    }



    override fun onResume() {
        super.onResume()
        viewmodel.getuserdata()
    }

    companion object {
        var userModel: LoginJson? = null
        var rollname = ""
        var leaveTypeListingJson:  LeaveTypeJson? = null
        var categoryTypeListingJson: LeaveCategoryJson? = null

    }

}