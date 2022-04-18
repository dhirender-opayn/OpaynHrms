package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.util.Util
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.CategoryLabelAdapter
import com.example.opaynhrms.adapter.LeaveCategoryAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.model.LeaveCategoryJson
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.AddUserRepository
import com.example.opaynhrms.repository.RequestRepository
import com.example.opaynhrms.repository.RolesJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.Multipart
import java.io.File


class AddUserViewModel(application: Application) : AppViewModel(application), View.OnClickListener,
    ItemClick {
    private lateinit var binder: ActivityAddUserBinding
    private lateinit var mContext: Context
    var userRepository: UserRepository = UserRepository(application)
    lateinit var baseActivity: KotlinBaseActivity
    var roleslist = ArrayList<String>()
    var mainrolelist = ArrayList<RolesJson.Data>()
    val bundle = Bundle()
    var roleid = ""
    var userid = ""
    var file: File? = null
    var requestRepository: RequestRepository = RequestRepository(application)
    val leaveCategorylist = ArrayList<LeaveCategoryJson.Data>()
    val categorylist = ArrayList<String>()
    var categoryid = ""
    var flag = false
    var position = 0
    var addUserRepository: AddUserRepository = AddUserRepository(application)
    var catId = ""
    var arrayCatId: ArrayList<String> = ArrayList<String>()
    var getuserData: LoginJson.Data? = null
    var totalleave = 0
    var categoryIdString = ""
    var ansarray = JSONArray()
    var ansarrayObj = JSONObject()
    var hashMap = HashMap<String, String>()
    var r = ArrayList<String>()
    var jsonloop = ArrayList<JSONObject>()
    fun setBinder(binder: ActivityAddUserBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {

            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            binder.tvtitle.text = baseActivity.getString(R.string.edituser)
            binder.singupbtn.text = baseActivity.getString(R.string.update)
            setdata(userdata)
        }

        setclicks()
        getroles()
//        leaveCategory()
        leaveListing()
//        leaveCategoryAdapter(categorylist)
//        categoryadpterLabel(categorylist)

    }


    private fun leaveListing() {

        if (Home.categoryTypeListingJson?.data.isNotNull() && Home.categoryTypeListingJson?.data!!.size > 0) {

            leaveCategorylist.addAll(Home.categoryTypeListingJson!!.data)
            Home.categoryTypeListingJson!!.data.forEach {
                categorylist.add(it.category)
            }
            Home.categoryTypeListingJson?.data.let {
                it?.let { it1 -> categoryadpterLabel(it1) }
            }

        }
    }


    private fun getroles() {
        userRepository.getroles(baseActivity) {
            mainrolelist.clear()
            mainrolelist.addAll(it.data)
            it.data.forEach {
                roleslist.add(it.name)
            }
            testtypeadapter()
        }
    }

    private fun setdata(userdata: UserListJson.Data) {
        userid = userdata.id.toString()
        binder.tvName.setText(userdata.name)
        binder.tvEmail.setText(userdata.email)
        binder.tvMobile.setText(userdata.mobile)
        roleid = userdata.roles[0].id.toString()
        binder.passwordWrap.gone()
        binder.leavetypewrap.gone()
        binder.tvEmail.isEnabled = false
    }

    private fun testtypeadapter() {
        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, roleslist)
        binder.autoRole.setAdapter(aa)
        binder.autoRole.setFocusable(false)
        binder.autoRole.setFocusableInTouchMode(false)
        binder.autoRole.setOnClickListener(this)
        binder.autoRole.setKeyListener(null)
        binder.autoRole.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            roleid = mainrolelist[i].id.toString()
        })
    }

    fun setfile(file: File) {
        this.file = file
        Glide.with(baseActivity).load(file).diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).into(binder.ivprofileimg)
    }

    private fun validations(): Boolean {
        if (binder.tvName.text!!.trim().isEmpty()) {
            baseActivity.showtoast(baseActivity.getString(R.string.v_entername))
            return false
        } else if (binder.tvEmail.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_email))
            return false
        } else if (!isEmailValid(binder.tvEmail.text!!.trim().toString())) {
            showToast(mContext.getString(R.string.v_validemail))
            return false
        } else if (binder.tvMobile.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_phonenumber))
            return false
        }
        if (!binder.tvtitle.text.toString().equals(baseActivity.getString(R.string.edituser))) {
            if (binder.tvnewpassord.text.toString().isEmpty()) {
                showToast(mContext.getString(R.string.v_password))
                return false
                if (roleid.isEmpty()) {
                    showToast(mContext.getString(R.string.v_role))
                    return false
                }
            }
        }
        return true
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener(this)
        binder.singupbtn.setOnClickListener(this)
        binder.clsingupcontainer.setOnClickListener(this)
        binder.tvCategoryname.setOnClickListener(this)


        binder.maincoantainer.setOnClickListener {
            hideRvCategory()

        }

        binder.cvContainer.setOnClickListener {
            hideRvCategory()

        }


    }

    private fun hideRvCategory() {
        binder.rvCategory.gone()
        flag = false
    }

    private fun createUser() {


        var url = ""
        val fields = java.util.ArrayList<MultipartBody.Part>()
        Utils.getMultiPart(Keys.name, binder.tvName.text.toString())?.let { fields.add(it) }
        Utils.getMultiPart(Keys.email, binder.tvEmail.text.toString())?.let { fields.add(it) }
        Utils.getMultiPart(Keys.mobile, binder.tvMobile.text.toString())?.let { fields.add(it) }
        val leaveArray = JSONArray()
        Home.categoryTypeListingJson?.data?.forEach {
            if (it.count.isNotNull() && !it.count.isEmpty()) {
                val jsonObject = JSONObject()
                jsonObject.put("id", it.id.toString())
                jsonObject.put("leave", it.count)
                leaveArray.put(jsonObject)
            }
        }
        Log.e("jsonarray", leaveArray.toString())


//
//        Utils.getMultiPart(Keys.leaves, categoryIdString)?.let {
//            fields.add(it) }

        Utils.getMultiPart(Keys.leaves, leaveArray.toString())?.let {
            fields.add(it)
        }

        // create user
        if (!binder.tvtitle.text.toString().equals(baseActivity.getString(R.string.edituser))) {
            url = Keys.DELTEUSER
            Utils.getMultiPart(Keys.password, binder.tvnewpassord.text.toString())
                ?.let { fields.add(it) }
            Utils.getMultiPart(Keys.role_id, roleid)?.let { fields.add(it) }
        } else {
            url = Keys.UPDATEUSER
            Utils.getMultiPart(Keys.id, userid)?.let { fields.add(it) }
        }

        Utils.getMultiPart(Keys.clockify_key, "")?.let { fields.add(it) }
        if (file != null) {
            Utils.getMultiPart(Keys.image, file!!)?.let { fields.add(it) }
        }

        userRepository.adduser(baseActivity, url, fields) {
            if (!it.data.isNull()) {

                getuserData = it.data

                Log.e("ieieieiei", getuserData.toString())
                Log.e("ieieieiei", getuserData?.user!!.id.toString())

                if (userid.isEmpty()) {
//                    addleavecategoryapi()
//                    baseActivity.showtoast(context.getString(R.string.user_added_successfully))
                    baseActivity.customSnackBar(
                        baseActivity.getString(R.string.user_added_successfully),
                        false
                    )
                } else {
//                    baseActivity.showtoast(context.getString(R.string.user_updated_successfully))
                    baseActivity.customSnackBar(
                        baseActivity.getString(R.string.user_updated_successfully),
                        false
                    )
                }
                baseActivity.finish()
            }

        }
    }

//    private fun leaveCategory() {
//        requestRepository.leaveCategory(baseActivity) {
//            if (it.data.isNotNull()) {
//
//                leaveCategorylist.addAll(it.data)
//                it.data.forEach {
//                    categorylist.add(it.category)
//
//                }
////                leaveCategoryAdapter(categorylist)
//                categoryadpterLabel(leaveCategorylist)
//
//
//            }
//        }
//    }

    //custom category adaper
//    private fun leaveCategoryAdapter(_categorylist: ArrayList<String>) {
//        val adapter = LeaveCategoryAdapter(baseActivity) {
//            hideRvCategory()
////            categoryadpterLabel(_categorylist)
//
//
////            binder.llcoantiner.visible()
////            binder.tvcategory.setText(_categorylist[it])
////            binder.tvCategoryname.setText(_categorylist[it])
////            val catId = leaveCategorylist[it].id.toString()
////
////
////
////            binder.tvAddCateogry.setOnClickListener {
////                binder.llcoantiner.gone()
////                val text = binder.tvCategoryCount.text.toString()
////                Log.e("Hellodata", text)
////                binder.tvcategory.setText("")
////                binder.tvCategoryname.setText("")
////            }
//
//        }
//        adapter.addNewList(_categorylist)
//        binder.rvCategory.adapter = adapter
//    }

    private fun categoryadpterLabel(mCategorylist: List<LeaveCategoryJson.Data>) {
        val adapter = CategoryLabelAdapter(baseActivity) {
//            position = it
//
//            catId = mCategorylist[it].id.toString()
//            arrayCatId.add(catId)
//
//
//            hashMap.put("id", catId)
//            hashMap.put("value", mCategorylist[it].count)
//
//
//
//            categoryIdString += mCategorylist[it].count.toString() + ","
//
//            r.add(mCategorylist[it].count)


//            categoryIdString += map
        }


        adapter.addNewList(mCategorylist)

        binder.rvCategoryEdit.adapter = adapter
    }

    private fun addleavecategoryapi() {
        Log.e("categoryidnow", arrayCatId.toString())
        Log.e("catLogId::", catId.toString())


        val jsonObject = JSONObject()

        jsonObject.put(Keys.user_id, getuserData?.user!!.id.toString())
        jsonObject.put(Keys.leave_category_id2, ansarray.toString())
//        jsonObject.addProperty(Keys.leave_category_id2, catId.toString())
        jsonObject.put(Keys.total_leaves, totalleave)
        jsonObject.put(Keys.available_leaves, totalleave)

        addUserRepository.addleavecategory(baseActivity, "add-leave-details", jsonObject) {

        }


    }


//default category adapter
//    private fun leaveCategoryAdapter(_categorylist: ArrayList<String>) {
//        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, _categorylist)
//        binder.tvleaveCategory.setAdapter(aa)
//        binder.tvleaveCategory.setFocusable(false)
//        binder.tvleaveCategory.setFocusableInTouchMode(false)
//        binder.tvleaveCategory.setOnClickListener(this)
//        binder.tvleaveCategory.setKeyListener(null)
//        binder.tvleaveCategory.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            categoryid = leaveCategorylist[i].id.toString()
//        })
//    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.auto_role -> {
                baseActivity.showDropDown(binder.autoRole)
            }
            R.id.singupbtn -> {
                totalleave = 0
                Home.categoryTypeListingJson?.data?.forEach {
                    if (it.count.isNotNull() && !it.count.toString().isEmpty()) {
                        totalleave = totalleave + it.count.toInt()

                    }
                }
                if (validations()) {
                    createUser()
                }
            }
            R.id.clsingupcontainer -> {
                baseActivity.hideKeyboard()
            }
            R.id.tvCategoryname -> {
                if (!flag) {
                    binder.imArrow.setImageResource(R.drawable.ic_up_arrow)
                    binder.rvCategory.visible()
                    flag = true
                } else {
                    binder.imArrow.setImageResource(R.drawable.ic_down_arrow)
                    binder.rvCategory.gone()
                    flag = false
                }

            }

            R.id.clsingupcontainer -> {
                flag = false
                binder.rvCategory.gone()
            }


        }

    }

    override fun onItemViewClicked(position: Int, type: String) {

    }
}