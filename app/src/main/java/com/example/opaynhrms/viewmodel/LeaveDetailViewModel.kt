package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentLeaveDetailBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.showConfirmAlert
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.LeaveListJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.permissionx.guolindev.dialog.permissionMapOnS
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.*
import kotlinx.android.synthetic.main.item_leave_request.view.*


class LeaveDetailViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: FragmentLeaveDetailBinding
    private lateinit var mContext: Context

    var userRepository: UserRepository = UserRepository(application)
    var list = ArrayList<LeaveListJson.Data>()

    lateinit var baseActivity: KotlinBaseActivity
    var bundle = Bundle()
    var datauser: LeaveListJson.Data? = null



    fun setBinder(binder: FragmentLeaveDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        bundle = (mContext as Activity).intent.extras!!
        Log.e("checknownownownow", bundle.getString(Keys.id).toString())
        settoolbar()
        setclick()
        datauser = bundle.getSerializable(Keys.data) as LeaveListJson.Data
        setdata()
        attatchmentDownload()
    }

    private fun attatchmentDownload() {
        if (!datauser?.file.isNullOrEmpty()) {
            binder.attachment.setText(datauser!!.file.toString())
            binder.attachment.setOnClickListener {


                baseActivity.downloadImage(datauser!!.file)
            }

        }
    }


    private fun setdata() {
        binder.leavesTitle.setText(datauser?.reason)
        binder.application.setText(datauser?.reason)


        if (Home.rollname.equals(Utils.SUPERADMIN)) {
            when (datauser?.status) {
                0 -> {
                    binder.editbutton.setText(baseActivity.getString(R.string.approve))

                    binder.editbutton.setOnClickListener {
                        acceptreject(
                            datauser?.user_id.toString(),
                            datauser?.id.toString(),
                           datauser?.leave_category_id.toString(),
                            "1",
                            "Are you sure you want to approve the leave"
                        )
                    }
                    binder.cancelbutton.setOnClickListener {
                        acceptreject(
                            datauser?.user_id.toString(),
                            datauser?.id.toString(),
                            datauser?.leave_category_id.toString(),
                            "2",
                            "Are you sure yoi want to cancel the leave"
                        )
                    }
                }
                1 -> {
                    binder.editbutton.setText(baseActivity.getString(R.string.approved))


                }
                2 -> {
                    binder.editbutton.setText(baseActivity.getString(R.string.reject))


                }
            }

        } else {
            binder.editbutton.setText(baseActivity.getString(R.string.edit))
            binder.editbutton.setOnClickListener {
                if (datauser?.status!!.equals(0)) {

                }
            }
        }



        when (datauser?.status) {
            0 -> {
                if (Home.rollname.equals(Utils.SUPERADMIN)) {
                    binder.clbutton.visible()
                }
                binder.tvStatus.text = baseActivity.getString(R.string.pending)
                binder.tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_yellow))
            }
            1 -> {
                binder.tvStatus.text = baseActivity.getString(R.string.accept)
                binder.clbutton.gone()
                binder.tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_green))
            }
            2 -> {
                binder.tvStatus.text = baseActivity.getString(R.string.reject)
                binder.tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_red))
                binder.clbutton.gone()
            }
        }


        when (datauser?.leave_type) {
            1 -> {
                binder.leavesType.text = baseActivity.getString(R.string.singleday)
                binder.leavesDate.text = Utils.formateDateFromstring(
                    Utils.DATEFORMAT2,
                    Utils.DATEFORMAT,
                    datauser?.start_date!!.split(" ")[0]
                )
            }
            2 -> {
                val date1 = Utils.formateDateFromstring(
                    Utils.DATEFORMAT2,
                    Utils.DATEFORMAT,
                    datauser?.start_date!!.split(" ")[0]
                )
                val date2 = Utils.formateDateFromstring(
                    Utils.DATEFORMAT2,
                    Utils.DATEFORMAT,
                    datauser?.end_date!!.split(" ")[0]
                )
                binder.leavesDate.text = date1 + " " + date2
                binder.leavesType.text = baseActivity.getString(R.string.multipleday)
            }

            4 -> {
                binder.leavesDate.text = Utils.formateDateFromstring(
                    Utils.DATETIMEFORMAT,
                    Utils.DATEFORMATRIMEFORMAT2,
                    datauser!!.start_date
                )
                binder.leavesType.text = baseActivity.getString(R.string.shortleave)
            }
            5 -> {
                binder.leavesDate.text = Utils.formateDateFromstring(
                    Utils.DATEFORMAT2,
                    Utils.DATEFORMAT,
                    datauser?.start_date!!.split(" ")[0]
                )
                binder.leavesType.text = baseActivity.getString(R.string.first_half)
            }
            6 -> {
                binder.leavesDate.text = Utils.formateDateFromstring(
                    Utils.DATEFORMAT2,
                    Utils.DATEFORMAT,
                    datauser?.start_date!!.split(" ")[0]
                )
                binder.leavesType.text = baseActivity.getString(R.string.second_half)
            }

        }


    }


    private fun acceptreject(user_id: String, id: String,cateogory_id:String, type: String, msg: String) {
        baseActivity.showConfirmAlert(msg, "Ok", "Cancel", onConfirmed = {
            val jsonobj = JsonObject()
            jsonobj.addProperty(Keys.user_id, user_id)
            jsonobj.addProperty(Keys.id, id)
            jsonobj.addProperty(Keys.status, type)
            jsonobj.addProperty(Keys.leave_category_id2, cateogory_id)
            userRepository.commonpostwithtoken(baseActivity, Keys.LEAVESTATUS, jsonobj) {
                Log.e("eddddddddddddd","eeeeeeeeeeeeeeeeeeeeeee")

                baseActivity.onBackPressed()

            }

        }, onCancel = {
        })

    }

    private fun settoolbar() {

        binder.toolbar.tvtitle.setTextColor(
            ContextCompat.getColor(
                baseActivity,
                R.color.light_gre1
            )
        )
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = mContext.getString(R.string.leavedetails)
    }

    private fun setclick() {
        binder.toolbar.icmenu.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }

        }
    }
}