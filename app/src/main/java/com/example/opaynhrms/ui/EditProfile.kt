package com.example.opaynhrms.ui
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.viewmodel.EditProfileViewModel

class EditProfile : KotlinBaseActivity() {
    lateinit var binding: ActivityEditProfileBinding
    lateinit var viewmodel: EditProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        viewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        viewmodel.setBinder(binding, this)


    }
}