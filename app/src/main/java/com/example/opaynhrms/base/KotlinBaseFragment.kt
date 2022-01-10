package com.example.opaynhrms.base

import android.Manifest
import android.R
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.opaynhrms.listner.KotlinBaseListener
import com.example.opaynhrms.utils.SharedPreferenceManager
import com.github.mikephil.charting.charts.Chart
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_leave_detail.*
import org.koin.android.ext.android.inject

abstract class KotlinBaseFragment(@LayoutRes val view: Int = 0) : Fragment() {


    protected lateinit var baselistener: KotlinBaseListener
    var bundle=Bundle()
    protected val months = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    )
    protected val parties = arrayOf(
        "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
        "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
        "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
        "Party Y", "Party Z"
    )
    protected var tfRegular: Typeface? = null
    protected var tfLight: Typeface? = null


    var rationale = "Please provide permission location permission"
    val preferencemanger: SharedPreferenceManager by inject()



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is KotlinBaseListener) {
            baselistener = context
        } else {
            throw IllegalStateException("You Must have to extends your activity with KotlinBaseActivity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tfRegular = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Regular.ttf")
        tfLight = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Light.ttf")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }






    private fun showAlert(s: String) {

        baselistener?.showAlert(s)
    }

    @DrawableRes
    open fun setBreadCrumbsImage(): Int? {
        return null
    }

    open fun setBreadCrumbsTitle(): String {
        return ""
    }

//    fun nointernershowToast()
//    {
//        val myToast = Toast.makeText(activity,getString(R.string.internetconnection), Toast.LENGTH_SHORT)
//        myToast.setGravity(Gravity.CENTER,0,0)
//        myToast.show()
//
//
//    }
    fun showtoast(string: String)
    {
        val myToast = Toast.makeText(activity,string, Toast.LENGTH_SHORT)
        myToast.setGravity(Gravity.CENTER,0,0)
        myToast.show()

    }
    fun showProgress() {
        baselistener.showProgress()
    }

    fun hideProgress() {
        baselistener.hideProgress()
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

//barcode


    protected fun getRandom(range: Float, start: Float): Float {
        return (Math.random() * range).toFloat() + start
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity)
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_STORAGE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                saveToGallery()
            } else {
                Toast.makeText(requireContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    protected fun requestStoragePermission(view: View?) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Snackbar.make(
                requireView()!!,
                "Write permission is required to save image to gallery",
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(
                    R.string.ok
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSION_STORAGE
                    )
                }.show()
        } else {
            Toast.makeText(requireContext(), "Permission Required!", Toast.LENGTH_SHORT)
                .show()
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_STORAGE
            )
        }
    }

    protected fun saveToGallery(chart: Chart<*>, name: String) {
        if (chart.saveToGallery(name + "_" + System.currentTimeMillis(), 70)) Toast.makeText(
            requireContext(), "Saving SUCCESSFUL!",
            Toast.LENGTH_SHORT
        ).show() else Toast.makeText(requireContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
            .show()
    }



//    protected abstract fun saveToGallery()

    companion object {
        private const val PERMISSION_STORAGE = 0
    }





}