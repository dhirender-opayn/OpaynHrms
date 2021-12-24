package com.example.opaynhrms.adapter


 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.model.ListingModel
 import com.ieltslearning.base.BaseAdapter


class TotalLeaveStatusAdapter (val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) : BaseAdapter<ListingModel>(
    R.layout.item_total_leaves)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int)
    {
        holder.itemView.apply {

//            leave_type.setText(list[position].type.toString())
//            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).into(ivprofile)
        }

    }

    override fun getItemCount(): Int {
        return 6
    }
}