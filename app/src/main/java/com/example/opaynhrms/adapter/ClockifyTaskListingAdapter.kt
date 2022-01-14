package com.example.opaynhrms.adapter

import android.util.Log
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.model.ClockifyListJson
import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_clockifyworklisting.view.*


class ClockifyTaskListingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) : BaseAdapter<ClockifyListJson.Data>(R.layout.item_clockifyworklisting)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            tvtitle.text=list[position].description
            var enddate=""

            val startdate=Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATEFORMATRIMEFORMAT2,list[position].timeInterval.start.replace("T"," ").toString().replace("Z",""))
            if (list[position].timeInterval.end.isNotNull())
            {
                enddate=Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATEFORMATRIMEFORMAT2,list[position].timeInterval.end.replace("T"," ").toString().replace("Z",""))
            }
            timing.text=startdate+" "+enddate
        }
    }


}