package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.example.opaynhrms.viewmodel.AddUserViewModel


class AddUser: KotlinBaseActivity() {
    lateinit var binding: ActivityAddUserBinding
    lateinit var viewmodel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        viewmodel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }

}