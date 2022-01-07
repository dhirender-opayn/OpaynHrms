package com.example.opaynhrms.adapter

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.fragment.AttendaceListingReport
import com.example.opaynhrms.model.ReportingListingModel
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_reporting_detail.view.*


class EmpReportingDetailAdapter(
    val baseActivity: KotlinBaseActivity,
    val itemClick: (Int) -> Unit
) :
    BaseAdapter<ReportingListingModel>(
        R.layout.item_reporting_detail
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            sub_leave_type.setText(list[position].subtitle.toString())
            leavetitle.setText(list[position].title.toString())

            Glide.with(baseActivity).load(list[position].value)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(ivprofile)

            leaverequestcontainer.setOnClickListener {


                when (list[position].subtitle) {
                    baseActivity.getString(R.string.leave) -> {
                        Log.e("leaveclicknow", "000000000000000000000000000000000000")
                    baseActivity.navigateToFragment(AttendaceListingReport(baseActivity),baseActivity.bundle,true)
                    }

                    baseActivity.getString(R.string.attandance) -> {

                    }
                    baseActivity.getString(R.string.clockify) -> {

                    }

                    baseActivity.getString(R.string.contact_admin) -> {

                    }
                    baseActivity.getString(R.string.salary) -> {

                    }

                }
            }

        }
    }

}