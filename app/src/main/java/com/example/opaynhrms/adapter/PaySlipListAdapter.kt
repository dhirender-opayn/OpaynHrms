package com.example.opaynhrms.adapter

 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.ieltslearning.base.BaseAdapter


class PaySlipListAdapter (val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) : BaseAdapter<String>(
    R.layout.item_payslip_list)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int)
    {
        holder.itemView.apply {


        }

    }

    override fun getItemCount(): Int {
        return 25
    }
}