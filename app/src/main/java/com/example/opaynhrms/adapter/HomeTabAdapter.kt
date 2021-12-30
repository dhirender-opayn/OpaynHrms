package com.example.opaynhrms.adapter


 import com.bumptech.glide.Glide
 import com.bumptech.glide.load.engine.DiskCacheStrategy
 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.model.ListingModel
 import com.example.opaynhrms.ui.*
 import com.ieltslearning.base.BaseAdapter
 import kotlinx.android.synthetic.main.item_home_tab.view.*


class HomeTabAdapter (val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) : BaseAdapter<ListingModel>(
    R.layout.item_home_tab)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int)
    {
        holder.itemView.apply {
            leave_type.setText(list[position].type.toString())

            Glide.with(baseActivity).load(list[position].value).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(ivprofile)

            hometabcontianer.setOnClickListener {
                when(list[position].type)
                {
                    baseActivity.getString(R.string.employees)->{
                        baseActivity.openA(StaffListing::class)
                    }
                    baseActivity.getString(R.string.leave)->{
                        baseActivity.openA(LeaveManagement::class)
                    }
                    baseActivity.getString(R.string.salary)->{
                        baseActivity.openA(Payslip::class)
                    }
                    baseActivity.getString(R.string.requestleave)->{
                        baseActivity.openA(RequestLeave::class)
                    }
                    baseActivity.getString(R.string.emergencyleave)->{
                        baseActivity.openA(EmergencyLeaveList::class)
                    }
                    baseActivity.getString(R.string.attandancelist)->{
                        baseActivity.openA(AttendanceList::class)
                    }
                }


                itemClick(position)

            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}