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


class DetailAttendanceListAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<AttandanceListJson.Data.Data>(R.layout.item_attendance_list) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {


        }

    }

    override fun getItemCount(): Int {
        return 10
    }

}