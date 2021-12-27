package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.StaffDetail
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_list_staff.view.*


class StaffListingAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<ListingModel>(
        R.layout.item_list_staff
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {

            clstaftListcontainer.setOnClickListener {
                baseActivity.openA(StaffDetail::class)
            }

        }
    }


    override fun getItemCount(): Int {
        return 25
    }
}