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
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.viewmodel.EditProfileViewModel
import kotlinx.coroutines.async
import java.io.File
import id.zelory.compressor.Compressor

class EditProfile : KotlinBaseActivity()
{
    lateinit var binding: ActivityEditProfileBinding
    lateinit var viewmodel: EditProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        viewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        viewmodel.setBinder(binding, this)
        binding.icedit.setOnClickListener {
            startCrop()
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(this) // optional usage
            val fi = File(uriFilePath.toString())

            fi.let {
                if (it.isNotNull()) {
                    viewmodel.setfile(it)

                    // Glide.with(this).load(it).into(binding.ivprofile)
                    lifecycleScope.async {
                        val compressedImageFile = Compressor.compress(this@EditProfile, it)
                        viewmodel.setfile(compressedImageFile)
                    }

                }
            }

        }
        else
        {
            // an error occurred
            val exception = result.error

        }
    }

    private fun startCrop()
    {
        // start picker to get image for cropping and then use the image in cropping activity
        cropImage.launch(
            options {
                setGuidelines(CropImageView.Guidelines.ON)
                setOutputCompressFormat(Bitmap.CompressFormat.WEBP)
            }
        )
    }
}