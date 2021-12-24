package com.example.opaynhrms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.HomeTabAdapter
import com.example.opaynhrms.adapter.LeaveRequestAdapter
import com.example.opaynhrms.adapter.StatisticsAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.ListingModel
import com.ieltslearning.base.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_home_fragement.*


class HomeFragement(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(),
    View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragement, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }


    private fun setAdapter() {
        val levaelist = ArrayList<ListingModel>()
        levaelist.add(ListingModel(R.drawable.family_vacation,false,"Request Annual Leave"))
        levaelist.add(ListingModel(R.drawable.sick,false,"Request Sick Leave"))
        levaelist.add(ListingModel(R.drawable.meeting,false,"Request Meeting Leave"))

        val tablist = ArrayList<ListingModel>()
        tablist.add(ListingModel(R.drawable.ic_booking_confirmed,false,"Leave"))
        tablist.add(ListingModel(R.drawable.ic_vacation,false,"Attendance"))
        tablist.add(ListingModel(R.drawable.ic_payroll_salary,false,"Salary"))
        tablist.add(ListingModel(R.drawable.ic_analytics,false,"Benefits"))
        tablist.add(ListingModel(R.drawable.ic_calendar_line,false,"Calendar"))
        tablist.add(ListingModel(R.drawable.ic_employee,false,"Employees"))

      val leaveadapter = LeaveRequestAdapter(baseActivity) {

        }
        rv_request.adapter =leaveadapter
        leaveadapter.addNewList(levaelist)

        val tabAdapter = HomeTabAdapter(baseActivity) {

        }
        rv_tabs.adapter = tabAdapter
        tabAdapter.addNewList(tablist)







    }

    override fun onClick(p0: View?) {

    }

}