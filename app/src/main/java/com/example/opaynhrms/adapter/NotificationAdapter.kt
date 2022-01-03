package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_notification.view.*


class NotificationAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<String>(
        R.layout.item_notification
    ) {
    var flag = false
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            ivdonwn.setOnClickListener {
                if (!flag) {
                    ivdonwn.setImageResource(R.drawable.ic_up_arrow)
                    tvans.visible()
                    view1.visible()
                    flag = true
                } else {
                    ivdonwn.setImageResource(R.drawable.ic_down_arrow)
                    view1.gone()
                    tvans.gone()
                    flag = false
                }

            }

        }
    }


    override fun getItemCount(): Int {
        return 25
    }
}