package com.example.opaynhrms.auth

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModelProvider
import com.anggrayudi.storage.SimpleStorage
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.callback.StorageAccessCallback
import com.anggrayudi.storage.file.StorageType
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.example.opaynhrms.viewmodel.LoginViewModel
import com.permissionx.guolindev.PermissionX

class Login : KotlinBaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    private val storage = SimpleStorageHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setBinder(binding, this)
        askpermission()
        var arrayList=ArrayList<String>(

        )
        arrayList.add("pdf")
        binding.loginbtn.setOnClickListener {
            storage.openFilePicker(allowMultiple = false,  )
        }
        storage.onFileSelected={requestCode, files ->

            if (files.size>0)
            {
                Log.e("fileeeeeeeee",files[0].name.toString())
                Log.e("fileeeeeeeee2222",files[0].isFile.toString())
            }

        }
//        Handler(Looper.getMainLooper()).postDelayed({
//            //openA(SpeakingTest::class)
////            getuserdata()
//        }, 1000)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        storage.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Mandatory for Activity, but not for Fragment & ComponentActivity
        storage.storage.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Mandatory for Activity, but not for Fragment & ComponentActivity
        storage.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
//    private fun getuserdata() {
//        preferencemanger.getString(Keys.USERDATA).let {
//            if (it == null || it.toString().isEmpty()) {
//
//            } else {
//                bundle.putString(Keys.FROM, "1")
//                openA(Home::class, bundle)
//                finishAffinity()
//            }
//
//        }
//
//    }
    private fun askpermission()
    {
        val permissonList = ArrayList<String>()
        permissonList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissonList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        PermissionX.init(this)
            .permissions(permissonList)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    getString(R.string.permisionmsgfirst),
                    getString(R.string.ok),
                    getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    getString(R.string.manualpermission),
                    getString(R.string.ok),
                    getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (!allGranted)
                {
                    showtoast(getString(R.string.permissiondenied))
                }
            }
    }

}