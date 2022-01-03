package com.example.opaynhrms.adapter


import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.toast
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.model.UserDetailJson
import com.example.opaynhrms.ui.Home

import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.*


class LeavelistingAdapter(var name:String,val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<UserDetailJson.Data.Leaves>(
        R.layout.item_leave_detail_cart
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            leavetitle.text= name

            cvContainer.setOnClickListener {

            }
            if (Home.rollname.equals(Utils.SUPERADMIN))
            {
                when(list[position].status.toInt())
                {
                    0->
                    {
                        editbutton.setText(baseActivity.getString(R.string.approve))
                        editbutton.setOnClickListener {

                        }
                        cancelbutton.setOnClickListener {

                        }
                    }
                    1->
                    {
                        editbutton.setText(baseActivity.getString(R.string.approved))


                    }
                    2->{
                        editbutton.setText(baseActivity.getString(R.string.reject))


                    }
                }

            }
            else
            {
                editbutton.setText(baseActivity.getString(R.string.edit))
                editbutton.setOnClickListener {
                    if (list[position].status.equals(0))
                    {

                    }
                }
            }
            tvmsg.text=list[position].reason
            when(list[position].status.toInt())
            {
                0->{
                    if (Home.rollname.equals(Utils.SUPERADMIN))
                    {
                      //  clbutton.visible()
                    }
                    tvStatus.text=baseActivity.getString(R.string.pending)

                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_yellow))
                }
                1->{
                    tvStatus.text=baseActivity.getString(R.string.accept)
                    clbutton.gone()
                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_green))
                }
                2->{
                    tvStatus.text=baseActivity.getString(R.string.reject)
                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_red))
                        clbutton.gone()
                }
            }

            when(list[position].leave_type.toInt())
            {
                1->{
                    leave_type.text=baseActivity.getString(R.string.singleday)
                    tvDate.text=Utils.formateDateFromstring(Utils.DATEFORMAT2,Utils.DATEFORMAT,list[position].start_date.split(" ")[0])
                }
                2->{
                    val  date1=Utils.formateDateFromstring(Utils.DATEFORMAT2,Utils.DATEFORMAT,list[position].start_date.split(" ")[0])
                    val  date2=Utils.formateDateFromstring(Utils.DATEFORMAT2,Utils.DATEFORMAT,list[position].end_date.split(" ")[0])
                    tvDate.text=date1+" "+date2
                    leave_type.text=baseActivity.getString(R.string.multipleday)
                }

                4->{
                    tvDate.text=Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATEFORMATRIMEFORMAT2,list[position].start_date)
                    leave_type.text=baseActivity.getString(R.string.shortleave)
                }
                5->{
                    tvDate.text=Utils.formateDateFromstring(Utils.DATEFORMAT2,Utils.DATEFORMAT,list[position].start_date.split(" ")[0])
                    leave_type.text=baseActivity.getString(R.string.first_half)
                }
                6->{
                    tvDate.text=Utils.formateDateFromstring(Utils.DATEFORMAT2,Utils.DATEFORMAT,list[position].start_date.split(" ")[0])
                    leave_type.text=baseActivity.getString(R.string.second_half)
                }
            }




        }

    }



}