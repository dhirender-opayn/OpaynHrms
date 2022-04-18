package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.DetailAttendanceListAdapter
import com.example.opaynhrms.adapter.FaqAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.databinding.FragmentFaqBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.fragment.FaqFragment
import com.example.opaynhrms.model.FAQJson
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.FaqRepository
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import kotlinx.android.synthetic.main.common_search_bar.view.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class FaqFragmentViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: FragmentFaqBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var faqAdapter: FaqAdapter? = null
    var faqRepository: FaqRepository? = FaqRepository(application)
    var faqList: ArrayList<FAQJson.Data> = ArrayList<FAQJson.Data>()
    var keyword = ""
    var listr = java.util.ArrayList<String>()
    val fullfaqlist: ArrayList<FAQJson.Data> = ArrayList<FAQJson.Data>()

    fun setBinder(binder: FragmentFaqBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        faqApi()
        settoolbar()
        binder.filter.gone()
        searchListner()

    }


    private fun faqApi() {
        faqRepository?.faq(baseActivity) {
            if (it.data.isNotNull()) {
                faqList.addAll(it.data)
                fullfaqlist.addAll(it.data)
                setFaqAdapter()
            }
        }


    }


    private fun searchListner() {
        binder.searchToolbar.search_bar.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                // TODO: for set data if user search first time and without hit as blank searchword then its used to filter all listing (like searchword: allFAQlisting)  not for search listing like searchword = "what"
                faqList.clear()
                faqList.addAll(fullfaqlist)

                Utils.hideKeyBoard(baseActivity, v)
                keyword = binder.searchToolbar.search_bar.text.toString()
                Log.e("dddddddddddddddddddddd", binder.searchToolbar.search_bar.text.toString())
                val searchword = binder.searchToolbar.search_bar.text.toString()
                Log.e("eeeeeeeeee", searchword.toString())

                val conatiners = faqList.filter {

                    it.question.lowercase().contains(searchword.lowercase())

                }
                Log.e("eeeeeeddddddddddd", conatiners.toString())
                if (!searchword.isEmpty()) {
                    faqList.clear()
                    faqList.addAll(conatiners)
                } else {
                    faqList.clear()
                    faqList.addAll(fullfaqlist)
                }

                setFaqAdapter()


                return@OnEditorActionListener true
            }
            false
        })

//        binder.searchToolbar.search_bar.addTextChangedListener {
//            servicesdetail(it.toString())
//        }
    }


    private fun settoolbar() {
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.setTextColor(baseActivity.resources.getColor(R.color.black))
        binder.toolbar.tvtitle.setText(baseActivity.resources.getText(R.string.faq))
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    private fun setFaqAdapter() {
        faqAdapter = FaqAdapter(baseActivity) {
        }
        faqAdapter?.addNewList(faqList)
        binder.rvFaq.adapter = faqAdapter


    }
}