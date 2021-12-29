package com.example.opaynhrms.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.viewmodel.LoginViewModel

class Login : KotlinBaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setBinder(binding, this)
        Handler(Looper.getMainLooper()).postDelayed({
            //openA(SpeakingTest::class)
            getuserdata()
        }, 1000)
    }


    private fun getuserdata() {
        preferencemanger.getString(Keys.USERDATA).let {
            if (it == null || it.toString().isEmpty()) {

            } else {
                bundle.putString(Keys.FROM, "1")
                openA(Home::class, bundle)
                finishAffinity()
            }

        }

    }

}