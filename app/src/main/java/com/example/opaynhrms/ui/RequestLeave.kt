package com.example.opaynhrms.ui

import android.Manifest
import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.file.fullName
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.dailog.CaptionDialog
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.example.opaynhrms.viewmodel.RequestLeaveViewModel
import com.permissionx.guolindev.PermissionX
import java.io.*

class RequestLeave : KotlinBaseActivity() {
    lateinit var binding: ActivityRequestLeaveBinding
    lateinit var viewmodel: RequestLeaveViewModel
    private val storageHelper = SimpleStorageHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_leave)
        viewmodel = ViewModelProvider(this).get(RequestLeaveViewModel::class.java)
        viewmodel.setBinder(binding, this)
        storagehelper()
        binding.attachment.setOnClickListener {
            val permissonList = ArrayList<String>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                permissonList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                permissonList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                permissonList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
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
                    if (allGranted) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            storageHelper.openFilePicker()
                        }
//                            else{
//                                showdialog()
//                            }
                    }
//                        else
//                        {
//
//                            showtoast(getString(R.string.nopermissiongrant))
//                        }
                }
        }
    }

    fun storagehelper() {
        storageHelper.onFileSelected = { requestCode, files ->
            Log.e("ddddddddddddddd", files.toString())
            if (files.size > 0) {

                var file = File(files[0].uri.path.toString())
                Log.e("ddddddddddddddd111", file.toString())
                binding.attachment.setText(files[0].fullName)

                try {
                    val uri: Uri? = files[0].uri
                    val file = File(cacheDir, files[0].name)
                    Log.e("ddddddddddddddd222", files.toString())
                    val maxBufferSize = 1 * 1024 * 1024
                    try {
                        val inputStream: InputStream? = contentResolver.openInputStream(uri!!)
                        Log.e("InputStream Size", "Size $inputStream")
                        val bytesAvailable: Int = inputStream!!.available()
                        val bufferSize = Math.min(bytesAvailable, maxBufferSize)
                        val buffers = ByteArray(bufferSize)
                        val outputStream = FileOutputStream(file)
                        var read = 0
                        while (inputStream.read(buffers).also { read = it } != -1) {
                            outputStream.write(buffers, 0, read)
                        }
                        inputStream.close()
                        outputStream.close()
                        file.path
                        Log.e("File Path", "Path " + file.path)
                        file.length()
                        Log.e("File Size", "Size " + file.length())
                        if (file.length() > 0) {


                            val tempfile = File(file.path)
                            if (tempfile.isFile) {
                                Log.e("filepathsssss", tempfile.isFile.toString())
                                val cR: ContentResolver = getContentResolver()
                                val mime = MimeTypeMap.getSingleton()
                                val type: String? = mime.getExtensionFromMimeType(cR.getType(uri))
//                                if (type.equals("pdf") || type.equals("doc") || type.equals("png") ||
//                                    type.equals("jpeg") || type.equals("xlsx") || type.equals("jpg")
                                if (  type.equals("doc") || type.equals("png") ||
                                    type.equals("jpeg") ||  type.equals("jpg")
                                ) {
                                    val fileSizeInBytes: Long = tempfile.length()
                                    val fileSizeInKB = fileSizeInBytes / 1024
                                    val fileSizeInMB = fileSizeInKB / 1024
                                    if (fileSizeInMB <= 2) {
                                        viewmodel.attachments = tempfile
                                        Log.e("dddddddeeeeee", tempfile.toString())

                                        showcaptiondialog(tempfile, type.toString())

                                    } else {
                                        showtoast("You can upload media only upto 2 mb")
                                    }

                                    //viewModel.sendmessage("","2")

                                } else {
                                    showtoast("Please select valid file  Image or Document")
                                }
                            }

                        }
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: OutOfMemoryError) {
                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            // do stuff
        }
    }

    fun showcaptiondialog(tempfile: File, type: String) {
        val dialog = CaptionDialog(this, tempfile, type.toString()) {
            if (it.equals("**")) {
                viewmodel.attachments = null
            } else {
//                viewmodel.attachments = it
//                viewModel.sendmessage(it.toString(),"")
            }
        }
        dialog.show(supportFragmentManager, dialog.getTag())
    }

}