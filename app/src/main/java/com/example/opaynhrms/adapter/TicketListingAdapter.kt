package com.example.opaynhrms.adapter

import android.util.Log
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.TicketListingJson
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_notification.view.*
import kotlinx.android.synthetic.main.item_ticket_listing.view.*


class TicketListingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<TicketListingJson.Data>(
        R.layout.item_ticket_listing
    ) {
     override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            Log.e("dsfdsfddfdfdef",list[position].toString())
            ticketsubject.setText(list[position].subject.toString())
            tvname.setText(list[position].name.toString())
            tvname.setText(list[position].email.toString())
            if (list[position].mobile.isNotNull()){
                tvmobile.setText(list[position].mobile)
            }
            tvmsg.setText(list[position].description.toString())
        }
    }



}