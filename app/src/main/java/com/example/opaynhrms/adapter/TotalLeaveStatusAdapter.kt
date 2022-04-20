package com.example.opaynhrms.adapter


import android.util.Log
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.model.UserLeaveDetailJson
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_total_leaves.view.*


class TotalLeaveStatusAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<UserLeaveDetailJson.Data>(
        R.layout.item_total_leaves
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            Log.e("ddfdfdfddf", list.toString())
            if (list[position].leave_category.category.isNotNull()){
                leave_type.setText(list[position].leave_category.category.toString())
                leave_days.setText(list[position].available_leaves.toString() + " / ")
                tvTotalLeaveDays.setText(list[position].total_leaves.toString())
            }else{
                Log.e("00000000000000000", "2222222222222222222222222")
            }



//            leave_type.setText(list[position].type.toString())
//            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).into(ivprofile)
        }

    }

}