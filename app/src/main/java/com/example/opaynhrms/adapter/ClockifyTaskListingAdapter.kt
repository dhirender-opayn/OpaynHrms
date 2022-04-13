package com.example.opaynhrms.adapter

import android.util.Log
import com.bumptech.glide.util.Util
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.model.ClockifyEntriesJson
import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_clockifyworklisting.view.*


class ClockifyTaskListingAdapter(
    val baseActivity: KotlinBaseActivity,
    val itemClick: (Int) -> Unit
) : BaseAdapter<ClockifyEntriesJson.Data>(R.layout.item_clockifyworklisting) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        var date2 = ""
        var date2time = ""
        var date1 =""
        var date1time = ""
        holder.itemView.apply {
            if (list.size > 0 && !list.isNull()) {

                if (list[position].timeInterval?.start.isNotNull()){
                    val firstDateFormate = list[position].timeInterval!!.start.split("T")
                    date1 = firstDateFormate[0].toString()
                    //[20-05-1954, 20-:05:16] - [0] get the 0 index
                    date1time = firstDateFormate[1].split("Z")[0]
                }

                if (list[position].timeInterval?.end.isNotNull()){
                    val secondDateFormate = list[position].timeInterval!!.end.split("T")
                      date2 = secondDateFormate[0].toString()
                    //[20-05-1954, 20-:05:16] - [0] get the 0 index
                      date2time = secondDateFormate[1].split("Z")[0]
                } else{
                    date2 = "pending"
                }


                tvtitle.setText(list[position].description.toString())
                timing.setText(date1 + " "+ date1time  + " - " + date2 + " "+date2time)

            }

        }
    }


}