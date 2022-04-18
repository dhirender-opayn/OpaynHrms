package com.example.opaynhrms.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
import com.example.opaynhrms.databinding.FragmentFaqBinding
import com.example.opaynhrms.viewmodel.ClockifyListingViewModel
import com.example.opaynhrms.viewmodel.FaqFragmentViewModel


class FaqFragment(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {
    lateinit var binding: FragmentFaqBinding
    lateinit var viewModel:FaqFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faq, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaqFragmentViewModel::class.java)
        viewModel.setBinder(binding, baseActivity)

    }



}