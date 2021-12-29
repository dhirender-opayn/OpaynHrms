package com.example.opaynhrms.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log

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
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Utils
import com.example.opaynhrms.viewmodel.FragmentHomeViewModel
import com.example.opaynhrms.viewmodel.HomeViewModel
import com.example.opaynhrms.viewmodel.LoginViewModel
import com.ieltslearning.base.KotlinBaseFragment
import com.permissionx.guolindev.PermissionX
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
    lateinit var binding: FragmentHomeFragementBinding
    lateinit var viewModel: FragmentHomeViewModel
    val levaelist = ArrayList<ListingModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_fragement, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
        viewModel.setBinder(binding, baseActivity)
        setAdapter()
        settoolbar()
        setadapter()
    }

    private fun settoolbar()
    {
        toolbar.tvtitle.text = "Home"
    }


    private fun setAdapter() {

        val tablist = ArrayList<ListingModel>()
        tablist.add(ListingModel(R.drawable.ic_booking_confirmed, false, "Leave"))
        tablist.add(ListingModel(R.drawable.ic_attendance_list, false, "Attendance List"))
//        tablist.add(ListingModel(R.drawable.ic_payroll_salary, false, "Salary"))
        tablist.add(ListingModel(R.drawable.ic_calendar_line, false, "Calendar"))
        tablist.add(ListingModel(R.drawable.ic_employee, false, "Employees"))
        tablist.add(ListingModel(R.drawable.ic_emergency, false, "Emergency Leaves"))
//        tablist.add(ListingModel(R.drawable.family_vacation, false, "Request Leave"))


        val tabAdapter = HomeTabAdapter(baseActivity) {

        }
        rv_tabs.adapter = tabAdapter
        tabAdapter.addNewList(tablist)

    }

    private fun setadapter() {
        if (Home.userModel?.data?.user!!.roles.size > 0) {
            binding.postName.text = Home.userModel?.data?.user!!.roles[0].name
            binding.rvRequest.visible()
            if (binding.postName.text.toString().equals(Utils.SUPERADMIN)) {

                levaelist.add(ListingModel(R.drawable.ic_add_boy_user, false, "Add User"))
                levaelist.add(ListingModel(R.drawable.announcement_svg, false, "Add Announcement"))

            } else {
                levaelist.add(ListingModel(R.drawable.enter, false, "Check in"))
                levaelist.add(ListingModel(R.drawable.logout, false, "Check out"))
            }
            val leaveadapter = LeaveRequestAdapter(baseActivity) {
                if (it.equals(-1)) {
                    askpermission()
                    //  scanQrCode.launch(null)
                }
            }
            rv_request.adapter = leaveadapter
            leaveadapter.addNewList(levaelist)
        }
    }
    private fun   askpermission()
    {
        val permissonList = ArrayList<String>()
        permissonList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissonList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        PermissionX.init(baseActivity)
            .permissions(permissonList)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    baseActivity.getString(R.string.permisionmsgfirst),
                    baseActivity.getString(R.string.ok),
                    baseActivity.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    baseActivity.getString(R.string.manualpermission),
                    baseActivity.getString(R.string.ok),
                    baseActivity.getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {

                } else {

                }
            }
    }

    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)

    fun handleResult(result: QRResult) {

    }

    override fun onClick(p0: View?) {

    }


}