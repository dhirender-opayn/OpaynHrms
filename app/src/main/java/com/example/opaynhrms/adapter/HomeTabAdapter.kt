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

                if (list[position].type.equals("Employees")){
                    baseActivity.openA(StaffListing::class)
                }
                if (list[position].type.equals("Leave")){
                    baseActivity.openA(LeaveManagement::class)
                }
                if (list[position].type.equals("Salary")){
                    baseActivity.openA(Payslip::class)
                }
                if (list[position].type.equals("Attendance List")){
                    baseActivity.openA(AttendanceList::class)
                }

                if (list[position].type.equals("Request Leave")){
                    baseActivity.openA(RequestLeave::class)
                }
                if (list[position].type.equals("Emergency Leaves")){
                    baseActivity.openA(EmergencyLeaveList::class)
                }
                itemClick(position)

            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}