package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.CommonModelCategoryList
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_category.view.*

class LeaveCategoryAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<String>(
        R.layout.item_category
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            tvcategory.setText(list[position])
           holder.itemView.setOnClickListener {
               itemClick(position)
           }
        }
    }
}