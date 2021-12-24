package com.example.opaynhrms.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.ui.ChangePassword
import com.example.opaynhrms.ui.EditProfile
import com.example.opaynhrms.ui.LeaveManagement
import com.ieltslearning.base.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(),
    View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        click()

    }

    private fun click() {
        leavemangement.setOnClickListener(this)
        ivedit.setOnClickListener(this)
        changepassword.setOnClickListener(this)

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
        }


    }

}