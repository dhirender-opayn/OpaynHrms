package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddAnnouncementBinding
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AddAnnouncement : KotlinBaseActivity() {
    lateinit var binding: ActivityAddAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_announcement)

        setclicks()


    }




    private fun setclicks() {
        binding.toolbar.icmenu.setOnClickListener {
            onBackPressed()
        }
    }
}