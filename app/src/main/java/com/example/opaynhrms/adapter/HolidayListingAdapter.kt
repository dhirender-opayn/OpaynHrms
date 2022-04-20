package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.HolidayListingJson
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_holiday_list.view.*
import kotlinx.android.synthetic.main.item_notification.view.*
import kotlinx.android.synthetic.main.item_notification.view.tvdate
import kotlinx.android.synthetic.main.item_notification.view.tvtitle


class HolidayListingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<HolidayListingJson.Data>(
        R.layout.item_holiday_list
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            tvtitle.setText(list[position].title)
            tvdate.setText(list[position].date)
            tvreason.setText(list[position].description)
        }
    }


}