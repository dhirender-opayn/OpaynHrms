package com.example.opaynhrms.fragment

import android.graphics.Bitmap
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentAddHolidayBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.viewmodel.AddHolidayViewModel
import com.example.opaynhrms.base.KotlinBaseFragment
import id.zelory.compressor.Compressor
import kotlinx.coroutines.async
import java.io.File

class AddHoliday(val baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: FragmentAddHolidayBinding
    lateinit var viewmodel: AddHolidayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_holiday, container, false)
//        binding = DataBindingUtil.setContentView(baseActivity, R.layout.fragment_add_holiday)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(AddHolidayViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)


        binding.icedit.setOnClickListener {
            startCrop()
        }


    }





    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(baseActivity) // optional usage
            val fi = File(uriFilePath.toString())

            fi.let {
                if (it.isNotNull()) {
                    viewmodel.setfile(it)

                    // Glide.with(this).load(it).into(binding.ivprofile)
                    lifecycleScope.async {
                        val compressedImageFile = Compressor.compress(baseActivity, it)
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