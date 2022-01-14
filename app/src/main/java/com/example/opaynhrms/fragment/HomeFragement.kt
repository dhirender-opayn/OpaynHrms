package com.example.opaynhrms.fragment

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.HomeTabAdapter
import com.example.opaynhrms.adapter.LeaveRequestAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentHomeFragementBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.ListingModel
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Utils
import com.example.opaynhrms.viewmodel.FragmentHomeViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.example.opaynhrms.base.KotlinBaseFragment
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.fragment_home_fragement.*
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.item_statistics_notification.*
import kotlinx.android.synthetic.main.statistics_notification.*


class HomeFragement(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(), View.OnClickListener,
    Listener
{
    lateinit var binding: FragmentHomeFragementBinding
    lateinit var viewModel: FragmentHomeViewModel
    val levaelist = ArrayList<ListingModel>()
    lateinit var location: EasyWayLocation
    var inout=""
    var lat=""
    var lng=""


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_fragement, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
        location = EasyWayLocation(baseActivity, false, false, this)
        viewModel.setBinder(binding, baseActivity)
        setAdapter()
        setsubheaderAdapter()
    }

    private fun setAdapter()
    {

        val tablist = ArrayList<ListingModel>()
        if (Home.userModel?.data?.user!!.roles.size > 0)
        {
            binding.postName.text = Home.userModel?.data?.user!!.roles[0].name
            binding.rvRequest.visible()
            if (binding.postName.text.toString().equals(Utils.SUPERADMIN)) {
                tablist.add(ListingModel(R.drawable.ic_booking_confirmed, false, getString(R.string.leave)))
                //tablist.add(ListingModel(R.drawable.ic_attendance_list, false, "Attendance List"))
                tablist.add(ListingModel(R.drawable.ic_calendar_line, false, getString(R.string.calendar)))
                tablist.add(ListingModel(R.drawable.ic_employee, false, getString(R.string.employees)))
                tablist.add(ListingModel(R.drawable.ic_emergency, false, getString(R.string.emergencyleave)))
                tablist.add(ListingModel(R.drawable.holiday, false, getString(R.string.addholiday)))

            } else {
                tablist.add(ListingModel(R.drawable.ic_booking_confirmed, false, getString(R.string.leave)))
                tablist.add(ListingModel(R.drawable.ic_attendance_list, false, getString(R.string.attandancelist)))
                tablist.add(ListingModel(R.drawable.ic_calendar_line, false, getString(R.string.calendar)))
                tablist.add(ListingModel(R.drawable.ic_travelling, false, getString(R.string.requestleave)))
                tablist.add(ListingModel(R.drawable.announcement_svg, false, getString(R.string.announcement)))
                tablist.add(ListingModel(R.drawable.ic_work_history, false, getString(R.string.workhistory)))

            }
        }
        val tabAdapter = HomeTabAdapter(baseActivity) {

        }
        rv_tabs.adapter = tabAdapter
        tabAdapter.addNewList(tablist)

    }
    private fun setsubheaderAdapter()
    {
        if (Home.userModel?.data?.user!!.roles.size > 0)
        {
            binding.postName.text = Home.userModel?.data?.user!!.roles[0].name
            Home.rollname=binding.postName.text.toString()
            binding.rvRequest.visible()
            if (binding.postName.text.toString().equals(Utils.SUPERADMIN)) {

                levaelist.add(ListingModel(R.drawable.ic_add_boy_user, false, getString(R.string.add_user)))
                levaelist.add(ListingModel(R.drawable.announcement_svg, false, getString(R.string.add_announcement)))

            } else {
                levaelist.add(ListingModel(R.drawable.enter, false, getString(R.string.checkIn)))
                levaelist.add(ListingModel(R.drawable.logout, false, getString(R.string.checkout)))
            }
            val leaveadapter = LeaveRequestAdapter(baseActivity) {
                if (it.equals(-1)||it.equals(-2)) {
                    askpermission(it)
                    //  scanQrCode.launch(null)
                }
            }
            rv_request.adapter = leaveadapter
            leaveadapter.addNewList(levaelist)
        }
    }
    private fun   askpermission(type:Int)
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
                if (allGranted)
                {
                    when(type)
                    {
                        -1->{
                            inout="IN"


                        }
                        -2->{
                            inout="OUT"


                        }
                    }
                    if (lat.isEmpty())
                    {
                        showtoast("Location is needed")
                        return@request
                    }
                    if (calculateDistance()<=30)
                    {
                        //showtoast("totaldistanceee${calculateDistance().toString()}")
                        viewModel.addorupdateAttandance(lat,lng,Utils.getcurrentdate(),inout)
                    }
                    else{
                        showtoast(getString(R.string.distancerange))
                    }

                }
            }
    }

    override fun onResume() {
        super.onResume()
        location.startLocation()

    }

    override fun onPause() {
        super.onPause()
        location.endUpdates()

    }
    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)

    fun handleResult(result: QRResult)
    {

    }
    private  fun calculateDistance():Int
    {
        val srclat=LatLng(lat.toDouble(),lng.toDouble())
        val deslat=LatLng(Utils.OPAYN_LAT.toDouble(),Utils.OPAYN_LNG.toDouble())
        val  distance= SphericalUtil.computeDistanceBetween(srclat, deslat);
         return  distance.toInt()
    }

    override fun onClick(p0: View?) {

    }

    override fun locationOn() {

    }
    override fun currentLocation(location: Location?)
    {
        if (location.isNotNull())
        {
            lat=location?.latitude.toString()
            lng=location?.longitude.toString()

            calculateDistance()
        }

    }

    override fun locationCancelled() {

    }


}