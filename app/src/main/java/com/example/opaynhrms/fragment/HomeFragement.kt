package com.example.opaynhrms.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.LeaveRequestAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
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
        rv_request.adapter = LeaveRequestAdapter(baseActivity) {

        }

    }

    override fun onClick(p0: View?) {

    }

}