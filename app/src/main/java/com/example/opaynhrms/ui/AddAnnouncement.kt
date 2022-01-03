package com.example.opaynhrms.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddAnnouncementBinding
import com.example.opaynhrms.extensions.hideKeyboard
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AddAnnouncement : KotlinBaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityAddAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_announcement)

        setclicks()


    }


    private fun validation(): Boolean {

        if (binding.tvTitle.text.toString().trim().isEmpty()) {
            showtoast(getString(R.string.titlefield))
            return false
        }
        if (binding.tvAnnouncement.text.toString().trim().isEmpty()) {
            showtoast(getString(R.string.announcement_msg))
            return false
        }
        return true


    }


    private fun setclicks() {
        binding.toolbar.icmenu.setOnClickListener(this)
        binding.loginbtn.setOnClickListener(this)
        binding.cvContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.loginbtn -> {
                if (validation()) {
                    showtoast("Add Announcement Successfully ")
                }
            }
            R.id.icmenu -> {
                onBackPressed()
            }
            R.id.cvContainer -> {
                hideKeyboard()
            }
        }
    }
}