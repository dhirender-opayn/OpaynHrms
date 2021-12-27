package com.example.opaynhrms.adapter


import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.ListingModel
import com.ieltslearning.base.BaseAdapter


class AttendanceListAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<ListingModel>(
        R.layout.item_attendance_list
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {


        }

    }

    override fun getItemCount(): Int {
        return 25
    }
}