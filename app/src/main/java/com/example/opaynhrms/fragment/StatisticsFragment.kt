package com.example.opaynhrms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.StatisticsAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.ieltslearning.base.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_home_fragement.*
import kotlinx.android.synthetic.main.fragment_home_fragement.rv_request
import kotlinx.android.synthetic.main.statistics_notification.*


class StatisticsFragment (var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.statistics_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val statisticsAdapter = StatisticsAdapter(baseActivity) {

        }
        rv_statistics.adapter = statisticsAdapter
//        tabAdapter.addNewList(tablist)
    }

    override fun onClick(p0: View?) {

    }


}