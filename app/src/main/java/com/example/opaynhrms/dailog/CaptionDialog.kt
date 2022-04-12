package com.example.opaynhrms.dailog

 import android.os.Bundle
 import android.util.Log
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.CaptiondialogBinding
 import com.example.opaynhrms.extensions.gone
 import com.opaynkart.ui.dialog.DialogBaseFragment
 import java.io.File

class CaptionDialog(var baseActivity: KotlinBaseActivity, val file:File, var  extension :String, val itemClick: (String) -> Unit) : DialogBaseFragment() {

    var values = ArrayList<String>()

     lateinit var binding: CaptiondialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.captiondialog,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        if (extension.equals("png")||extension.equals("jpg")||extension.equals("jpeg"))
        {
            Log.e("deeeeeeeeeeeeeeeeeeeee",file.toString())
            Glide.with(baseActivity).load(file).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(binding.previewImage)
            binding.preeviwattachemnt.gone()
        }

        setclisk()

    }
    private  fun setclisk()
    {
        binding.tvcancel.setOnClickListener {
            itemClick("**")
            dismiss()
        }
        binding.tvokay.setOnClickListener {
//            itemClick( binding.clbottom.msg.text.toString())
            dismiss()
        }
    }


}