package com.example.opaynhrms.fragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentEmployeedetailBinding
 import com.example.opaynhrms.viewmodel.EmployeeViewModel
import com.example.opaynhrms.base.KotlinBaseFragment

class EmployeeFragment(val baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: FragmentEmployeedetailBinding
    lateinit var viewmodel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employeedetail, container, false)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)




    }



}