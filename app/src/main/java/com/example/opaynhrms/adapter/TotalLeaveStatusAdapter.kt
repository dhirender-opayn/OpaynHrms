package com.example.opaynhrms.adapter


 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.model.ListingModel
 import com.example.opaynhrms.model.UserLeaveDetailJson
 import com.ieltslearning.base.BaseAdapter
 import kotlinx.android.synthetic.main.item_total_leaves.view.*


class TotalLeaveStatusAdapter (val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) : BaseAdapter<UserLeaveDetailJson.Data.Original.Data>(
    R.layout.item_total_leaves)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int)
    {
        holder.itemView.apply {
//            leave_type.setText(list[position].leave_Category_id)
            leave_days.setText(list[position].available_leaves.toString() + " / ")
            tvTotalLeaveDays.setText(list[position].total_leaves.toString())

//            leave_type.setText(list[position].type.toString())
//            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).into(ivprofile)
        }

    }

}