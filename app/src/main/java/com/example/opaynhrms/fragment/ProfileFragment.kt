package com.example.opaynhrms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.common.CommonActivity
import com.example.opaynhrms.databinding.FragmentProfileBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.ui.*
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.viewmodel.ProfileViewModel
import com.example.opaynhrms.base.KotlinBaseFragment
import com.example.opaynhrms.extensions.visible
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(),
    View.OnClickListener {
    lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel
    var getbundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.setBinder(binding, baseActivity)
        click()


    }

    private fun click() {
        binding.leavemangement.setOnClickListener(this)
        binding.ivedit.setOnClickListener(this)
        binding.changepassword.setOnClickListener(this)
        binding.notification.setOnClickListener(this)
        binding.loginbtn.setOnClickListener(this)
        binding.support.setOnClickListener(this)
        binding.addTicket.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.setdata()

    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.leavemangement -> {
                baseActivity.openA(LeaveManagement::class)
            }
            R.id.ivedit -> {
                baseActivity.openA(EditProfile::class)
            }
            R.id.changepassword -> {
                baseActivity.openA(ChangePassword::class)
            }
            R.id.notification -> {
                bundle.putString(Keys.FROM,baseActivity.getString(R.string.notification))
                baseActivity.openA(CommonActivity::class,bundle)
            }
            R.id.support -> {
                baseActivity.openA(Support::class)
            }
            R.id.add_ticket -> {
                var bundle = Bundle()
                bundle.putString(Keys.FROM,baseActivity.getString(R.string.add_ticket))
                baseActivity.openA(CommonActivity::class,bundle)
            }
            R.id.loginbtn -> {
                baseActivity.logout()
            }
        }


    }

}