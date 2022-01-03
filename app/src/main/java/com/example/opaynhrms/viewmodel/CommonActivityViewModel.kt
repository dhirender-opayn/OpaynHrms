package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.databinding.ActivityCommonBinding


class CommonActivityViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: ActivityCommonBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var bundle = Bundle()


    fun setBinder(binder: ActivityCommonBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        bundle = (mContext as Activity).intent.extras!!
       var r = bundle.get(mContext.getString(R.string.leavemanagment))
        Log.e("Ederefefefefef",r.toString())
    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {

        }

    }
}