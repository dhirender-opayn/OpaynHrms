package com.example.opaynhrms.adapter

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.AddAnnouncement
import com.example.opaynhrms.ui.AddUser
import com.example.opaynhrms.ui.RequestLeave
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_leave_request.view.*

class LeaveRequestAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<ListingModel>(
        R.layout.item_leave_request
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {

            leave_type.setText(list[position].type.toString())
            Glide.with(baseActivity).load(list[position].value)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(ivprofile)

            leaverequestcontainer.setOnClickListener {
                when(list[position].type)
                {
                    "Attendance"->{
                        itemClick(position)
                    }
                    "Add User"->{
                        baseActivity.openA(AddUser::class)
                    }
                    "Add Announcement"->{
                        baseActivity.openA(AddUser::class)
                    }
                    "Check in"->{
                        itemClick(-1)
                    }
                    "Check out"->{
                        itemClick(-2)
                    }
                    else->{
                        baseActivity.openA(RequestLeave::class)
                    }

                }
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}