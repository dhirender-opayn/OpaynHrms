package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.CategoryLabelAdapter
import com.example.opaynhrms.adapter.LeaveCategoryAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.model.CommonModelCategoryList
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
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import org.json.JSONArray
import org.json.JSONObject
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
    var editCategorylist = ArrayList<String>()
    var jsonloop = ArrayList<JSONObject>()
    var cateogryListnow: List<LeaveCategoryJson.Data>? = null
    var categorylistnow: ArrayList<CommonModelCategoryList>? = ArrayList()
    fun setBinder(binder: ActivityAddUserBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        leaveListing()
        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {
            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            Log.e("eeeee0202220202", userdata.toString())
            binder.tvtitle.text = baseActivity.getString(R.string.edituser)
            binder.singupbtn.text = baseActivity.getString(R.string.update)
            setdata(userdata)
            if (userdata.leave_details.isNotNull()) {
                categorylistnow?.clear()
                Log.e("0000000000000000000000",userdata.leave_details.size.toString())
                userdata.leave_details.forEach {
//                    Log.e("8888888888888888888888",it.leave_category.category.toString())

                    if (!it.leave_category?.category.isNull()){
                        categorylistnow?.add(
                            CommonModelCategoryList(
                                it.leave_category?.category!!,
                                it.leave_category_id,
                                it.total_leaves.toString(),
                                it.total_leaves.toString(),
                                true
                            )
                        )

                    }

                }
                categoryadpterLabel()

            }

//            categoryadpterLabel(cateogryListnow!!)
        }

        setclicks()
        getroles()
//        leaveCategory()

        leaveCategoryAdapter(categorylist)
//        categoryadpterLabel(categorylist)
    }

    private fun leaveListing() {

        if (Home.categoryTypeListingJson?.data.isNotNull() && Home.categoryTypeListingJson?.data!!.size > 0) {

            leaveCategorylist.addAll(Home.categoryTypeListingJson!!.data)
            Home.categoryTypeListingJson!!.data.forEach {
                categorylist.add(it.category)
            }
            Home.categoryTypeListingJson?.data.let {
                it?.let { it1 ->
                    cateogryListnow = it1
//                    categoryadpterLabel(it1)

                    it1.forEach {
                        Log.e("02020202020202020", it.toString())

                        categorylistnow?.add(
                            CommonModelCategoryList(
                                it.category.toString(),
                                it.id,
                                "",
                                it.count,
                                false
                            )
                        )

                    }
                    categoryadpterLabel()
                }
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


        categorylistnow?.forEach {
            Log.e("msgmsgmsmgsmgmgsmsgm",it.toString())
            val jsonObject = JSONObject()
            if (it.count.isNotNull() && !it.count?.isEmpty()!!) {

                jsonObject.put("id", it.id.toString())
                jsonObject.put("leave", it.count)
                leaveArray.put(jsonObject)
            }
            else{
                jsonObject.put("id", it.id.toString())
                jsonObject.put("leave", "0")
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



    //custom category adaper
    private fun leaveCategoryAdapter(_categorylist: ArrayList<String>) {


        val adapter = LeaveCategoryAdapter(baseActivity) {
            hideRvCategory()


        }
        adapter.addNewList(_categorylist)
        binder.rvCategory.adapter = adapter
    }

    private fun categoryadpterLabel( ) {
        val adapter = CategoryLabelAdapter(baseActivity) {

        }
        adapter.addNewList(categorylistnow)
        binder.rvCategoryEdit.adapter = adapter
    }



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