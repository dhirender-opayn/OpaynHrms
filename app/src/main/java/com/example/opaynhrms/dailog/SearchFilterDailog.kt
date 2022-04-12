package com.example.opaynhrms.dailog

import android.os.Bundle

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.SearchFilterDailogBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.listner.FilterListner
import com.example.opaynhrms.utils.Utils
import com.opaynkart.ui.dialog.DialogBaseFragment


class SearchFilterDailog(var baseActivity: KotlinBaseActivity, var filterListner: FilterListner) :
    DialogBaseFragment() {
    lateinit var binding: SearchFilterDailogBinding
    var radiotype = ""
    var isdateshow = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.search_filter_dailog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setclick()


        binding.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
                radiotype = radio.text.toString().lowercase()
                when (radio.text.toString()) {
                    baseActivity.getString(R.string.weekly) -> {

                        binding.datecontainer.gone()
                    }
                    baseActivity.getString(R.string.monthly) -> {

                        binding.datecontainer.gone()
                    }
                    baseActivity.getString(R.string.custom) -> {

                        binding.datecontainer.visible()
                    }
                }

            })


    }

    private fun setclick() {
        binding.startdate.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                return true
            }

        })
        binding.enddate.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                return true
            }

        })
        binding.endwrap.setEndIconOnClickListener {

            if (binding.startdate.text.toString().trim().isEmpty()) {
                baseActivity.showtoast("Please select start date first")
                return@setEndIconOnClickListener
            }
            if (isdateshow) {
                isdateshow = false
                Utils.shoedatepicker(baseActivity, binding.enddate, onConfirmed = {
                    isdateshow = true
                })
            }

        }
        binding.startdatewrap.setEndIconOnClickListener {
            if (isdateshow) {
                isdateshow = false
                Utils.shoedatepicker(baseActivity, binding.startdate, onConfirmed = {
                    isdateshow = true
                })
            }

        }
        binding.loginbtn.setOnClickListener {
            if (radiotype.equals(baseActivity.getString(R.string.custom).lowercase())) {
                if (validations()) {
                    val date1 = Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binding.startdate.text.toString()
                    )
                    val date2 = Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binding.enddate.text.toString()
                    )
                    filterListner.filterdata(radiotype, "$date1 00:00:00", "$date2 00:00:00")
                    dismiss()
                }

            } else {

                filterListner.filterdata(radiotype, "00:00:00", "00:00:00")
                dismiss()
            }
        }
    }

    private fun validations(): Boolean {
        if (binding.startdate.text.toString().trim().isEmpty()) {
            baseActivity.showtoast("Please select start date")
            return false
        }
        if (binding.enddate.text.toString().trim().isEmpty()) {
            baseActivity.showtoast("Please select end date")
            return false
        }
        return true

    }


}