package com.example.opaynhrms.adapter


import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.AttandanceListJson
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_attendance_list.view.*


class AttendanceListAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<AttandanceListJson.Data.Data>(R.layout.item_attendance_list) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {

            tvAttendanceStatus.text=Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATEFORMAT,list[position].timing.replace("T"," ").replace(".000000Z",""))
            if (list[position].type.equals("IN"))
            {
                tvCheckIn.setText("Check In")
                tvCheckInTime.setTextColor(Color.GREEN)
            }
            else{
                tvCheckIn.setText("Check Out")
                tvCheckInTime.setTextColor(Color.RED)
            }
            tvCheckInTime.text=Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.TIMEFORMAT,list[position].timing.replace("T"," ").replace(".000000Z",""))
            if (position>0)
            {
                if (Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATETIMEFORMAT,list[position].timing.replace("T"," ").replace(".000000Z","")).
                    equals(Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATETIMEFORMAT,list[position-1].timing.replace("T"," ").replace(".000000Z",""))))
                {
                     cvContainer.gone()
                }
                else
                {
                    cvContainer.visible()
                }
            }
            else
            {
                cvContainer.visible()
            }

        }

    }

}