package com.example.opaynhrms.adapter

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.toast
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.ui.RequestLeave
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.*
import kotlinx.android.synthetic.main.item_leave_request.view.*

class LeavelistingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<ListingModel>(
        R.layout.item_leave_detail_cart
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {


            if (Home.userModel?.data?.user!!.roles[0].name.equals(Utils.SUPERADMIN)) {
                editbutton.setText(baseActivity.getString(R.string.approved))
                editbutton.setOnClickListener {
                    baseActivity.showtoast("Leave is approved")
                }
            } else {

                editbutton.setText(baseActivity.getString(R.string.edit))
                editbutton.setOnClickListener {
                    baseActivity.openA(RequestLeave::class)
                }

            }


//            leave_type.setText(list[position].type.toString())
//            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).into(ivprofile)
        }

    }


    override fun getItemCount(): Int {
        return 6
    }
}