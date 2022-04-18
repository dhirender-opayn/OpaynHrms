package com.example.opaynhrms.adapter

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isEmpty
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.LeaveListJson
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Utils
import com.ieltslearning.base.BaseAdapter
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.item_attendance_list.view.*
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.*
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.cvContainer
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.tvDate
import kotlinx.android.synthetic.main.item_leave_detail_cart.view.tvStatus
import kotlinx.android.synthetic.main.item_leave_request.view.*
import kotlinx.android.synthetic.main.item_leave_request.view.leave_type

class LeaveDetailCartAdapter(val baseActivity: KotlinBaseActivity, val itemClick: ItemClick) :
    BaseAdapter<LeaveListJson.Data>(
        R.layout.item_leave_detail_cart
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {



            cvContainer.setOnClickListener {
                itemClick.onItemViewClicked(position, "3")
            }

             val date = Utils.formateDateFromstring(Utils.DATETIMEFORMAT,Utils.DATEFORMAT,list[position].created_at.replace("T"," ").replace(".000000Z",""))
            tvapplyDate.setText(date)

            if (Home.rollname.equals(Utils.SUPERADMIN)) {
                leavetitle.text = list[position].user.name
                when (list[position].status) {
                    0 -> {
                        editbutton.setText(baseActivity.getString(R.string.approve))
                        editbutton.setOnClickListener {
                            itemClick.onItemViewClicked(position, "1")
                        }
                        cancelbutton.setOnClickListener {
                            reasonwrap.visible()
                            if (!tvreason.isEmpty()) {
                                list[position].reason_for_rejection = tvreason.text.toString()
                                notifyDataSetChanged()
                                itemClick.onItemViewClicked(position, "2")

                            } else {
                                baseActivity.customSnackBar("Please Give Reason", true)
                            }

                        }
                    }
                    1 -> {
                        editbutton.setText(baseActivity.getString(R.string.approved))


                    }
                    2 -> {
                        editbutton.setText(baseActivity.getString(R.string.reject))
                        reasonwrap.visible()
                    }
                }

            } else {
                leavetitle.text =  list[position].reason
                editbutton.setText(baseActivity.getString(R.string.edit))
                if ( list[position].reject_reason.isNotNull() ) {
                    rejection_reasonTitle.visible()
                    tvlabel.gone()
                    reasonwrap.gone()
                    rejection_reason.visible()
                    rejection_reason.setText(list[position].reject_reason.toString())
                } else{
                    tvlabel.gone()
                    reasonwrap.gone()
                    rejection_reasonTitle.gone()
                    rejection_reason.gone()
                    rejection_reason.setText("")
                }

                editbutton.setOnClickListener {
                    if (list[position].status.equals(0)) {

                    }
                }
            }
            tvmsg.text = list[position].reason

            when (list[position].status) {
                0 -> {
                    if (Home.rollname.equals(Utils.SUPERADMIN)) {
                        clbutton.visible()
                        tvlabel.visible()
                        reasonwrap.visible()
                    }
                    tvStatus.text = baseActivity.getString(R.string.pending)
                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_yellow))
                }
                1 -> {
                    tvStatus.text = baseActivity.getString(R.string.accept)
                    clbutton.gone()
                    tvlabel.gone()
                    reasonwrap.gone()
                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_green))
                }
                2 -> {
                    tvStatus.text = baseActivity.getString(R.string.reject)
                    tvlabel.gone()
                    reasonwrap.gone()
                    tvStatus.setBackground(baseActivity.getDrawable(R.drawable.round_shape_red))
                    clbutton.gone()
                }
            }

            when (list[position].leave_type) {
                1 -> {
                    leave_type.text = baseActivity.getString(R.string.singleday)
                    tvDate.text = Utils.formateDateFromstring(
                        Utils.DATEFORMAT2,
                        Utils.DATEFORMAT,
                        list[position].start_date.split(" ")[0]
                    )
                }
                2 -> {
                    val date1 = Utils.formateDateFromstring(
                        Utils.DATEFORMAT2,
                        Utils.DATEFORMAT,
                        list[position].start_date.split(" ")[0]
                    )
                    val date2 = Utils.formateDateFromstring(
                        Utils.DATEFORMAT2,
                        Utils.DATEFORMAT,
                        list[position].end_date.split(" ")[0]
                    )
                    tvDate.text = date1 + " " + date2
                    leave_type.text = baseActivity.getString(R.string.multipleday)
                }

                4 -> {
                    tvDate.text = Utils.formateDateFromstring(
                        Utils.DATETIMEFORMAT,
                        Utils.DATEFORMATRIMEFORMAT2,
                        list[position].start_date
                    )
                    leave_type.text = baseActivity.getString(R.string.shortleave)
                }
                5 -> {
                    tvDate.text = Utils.formateDateFromstring(
                        Utils.DATEFORMAT2,
                        Utils.DATEFORMAT,
                        list[position].start_date.split(" ")[0]
                    )
                    leave_type.text = baseActivity.getString(R.string.first_half)
                }
                6 -> {
                    tvDate.text = Utils.formateDateFromstring(
                        Utils.DATEFORMAT2,
                        Utils.DATEFORMAT,
                        list[position].start_date.split(" ")[0]
                    )
                    leave_type.text = baseActivity.getString(R.string.second_half)
                }
            }
//            leave_type.setText(list[position].type.toString())
//            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).into(ivprofile)
        }

    }


}