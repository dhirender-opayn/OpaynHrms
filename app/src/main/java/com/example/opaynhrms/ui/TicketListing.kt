package com.example.opaynhrms.ui

import android.graphics.Bitmap
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.example.opaynhrms.databinding.TicketListingBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.viewmodel.AddUserViewModel
import com.example.opaynhrms.viewmodel.TicketListingViewModel
import id.zelory.compressor.Compressor
import kotlinx.coroutines.async
import java.io.File


class TicketListing: KotlinBaseActivity()
{
    lateinit var binding: TicketListingBinding
    lateinit var viewmodel: TicketListingViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ticket_listing)
        viewmodel = ViewModelProvider(this).get(TicketListingViewModel::class.java)
        viewmodel.setBinder(binding, this)

    }




}