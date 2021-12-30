package com.example.opaynhrms.dailog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentFilterDailogBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.opaynkart.ui.dialog.DialogBaseFragment






class FilterDailog (var baseActivity: KotlinBaseActivity) :
    DialogBaseFragment(), View.OnClickListener {
    lateinit var binding:FragmentFilterDailogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_dailog,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setclick()


       binding.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
//                Toast.makeText(baseActivity," On checked change :"+
//                        " ${radio.text}",
//                    Toast.LENGTH_SHORT).show()
                if (radio.text.equals(getString(R.string.custom))){
                    binding.datecontainer.visible()
                } else {
                    binding.datecontainer.gone()
                }

                Log.e("jekediemdidcidjded",radio.text.toString())
            })




    }

    private fun setclick(){

    }
    override fun onClick(p0: View?) {

    }


}