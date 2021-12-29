package com.example.opaynhrms.fragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.HomeTabAdapter
import com.example.opaynhrms.adapter.LeaveRequestAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentHomeFragementBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.invisible
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.viewmodel.FragmentHomeViewModel
import com.example.opaynhrms.viewmodel.HomeViewModel
import com.example.opaynhrms.viewmodel.LoginViewModel
import com.ieltslearning.base.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_home_fragement.*
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_home_fragement.toolbar
import kotlinx.android.synthetic.main.item_statistics_notification.*
import kotlinx.android.synthetic.main.statistics_notification.*


class HomeFragement(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(),
    View.OnClickListener {
    lateinit var binding:FragmentHomeFragementBinding
    lateinit var viewModel: FragmentHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_fragement, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
        viewModel.setBinder(binding,baseActivity)
        setAdapter()

        settoolbar()



    }




    private fun settoolbar(){
       binding.toolbar.tvtitle.text = getString(R.string.home)
        binding.toolbar.icmenu.invisible()
    }


    private fun setAdapter() {
        val levaelist = ArrayList<ListingModel>()
//        levaelist.add(ListingModel(R.drawable.family_vacation, false, "Request Leave"))
//        levaelist.add(ListingModel(R.drawable.attendence_machine, false, "Attendance"))
        levaelist.add(ListingModel(R.drawable.ic_add_boy_user, false, "Add User"))
        levaelist.add(ListingModel(R.drawable.announcement_svg, false, "Add Announcement"))

        val tablist = ArrayList<ListingModel>()
        tablist.add(ListingModel(R.drawable.ic_booking_confirmed, false, "Leave"))
        tablist.add(ListingModel(R.drawable.ic_attendance_list, false, "Attendance List"))
//        tablist.add(ListingModel(R.drawable.ic_payroll_salary, false, "Salary"))
        tablist.add(ListingModel(R.drawable.ic_calendar_line, false, "Calendar"))
        tablist.add(ListingModel(R.drawable.ic_employee, false, "Employees"))
        tablist.add(ListingModel(R.drawable.ic_emergency, false, "Emergency Leaves"))
//        tablist.add(ListingModel(R.drawable.family_vacation, false, "Request Leave"))

        val leaveadapter = LeaveRequestAdapter(baseActivity) {
            if (it.equals(1)) {
                scanQrCode.launch(null)
            }


        }
        rv_request.adapter = leaveadapter
        leaveadapter.addNewList(levaelist)

        val tabAdapter = HomeTabAdapter(baseActivity) {

        }
        rv_tabs.adapter = tabAdapter
        tabAdapter.addNewList(tablist)

    }



    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)


    fun handleResult(result: QRResult) {

    }

    override fun onClick(p0: View?) {

    }


}