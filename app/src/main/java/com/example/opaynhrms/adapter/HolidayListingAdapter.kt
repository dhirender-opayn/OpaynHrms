package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_notification.view.*


class HolidayListingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<String>(
        R.layout.item_holiday_list
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {


        }
    }


    override fun getItemCount(): Int {
        return 25
    }
}