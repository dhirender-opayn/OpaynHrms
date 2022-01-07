package com.example.opaynhrms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentEmpReprotingDetailBinding
import com.example.opaynhrms.viewmodel.EmpReportingDetailViewModell
import com.ieltslearning.base.KotlinBaseFragment


class EmpReprotingDetail(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: FragmentEmpReprotingDetailBinding
    lateinit var viewmodel:EmpReportingDetailViewModell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_reproting_detail, container, false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(EmpReportingDetailViewModell::class.java)
        viewmodel.setBinder(binding, baseActivity)
    }


}