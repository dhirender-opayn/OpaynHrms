package com.example.opaynhrms.utils

object Keys {
    //     const val  BASEURL="http://3.22.185.42/hrms-api/public/api/"
    const val BASEURL = "http://bb34-210-89-39-142.ngrok.io/hrms-api/public/api/"

    //const val  IMAGEBASEURL= BASEURL
    /*RESPONSE CODE*/
    val RESPONSE_SUCESS = 200
    val ERRORCODE = 412
    val UNAUTHoRISE = 401

    /*End points*/
    const val FORGOTPASSWORD = "forget-password"
    const val RESETPASSWORD = "reset-possword"
    const val CHANGEPASSWORD = "change-password"
    const val TICKET = "ticket"
    const val ATTANDANCE = "attendance"
    const val LEAVESTATUS = "leave/status"
    const val LEAVE_CATEGORY = "leave-categories"
    const val LEAVE_TYPE = "leave-types"
    const val DELTEUSER = "user"
    const val UPDATEUSER = "user/update"
    const val TEAMDATA = "team?user_id=";
    const val ATTANDANCELIST = "attendance/listing"
    const val USER_LEAVE_INFO = "user-leave-info/"
    const val USER_LEAVE_DETAILS = "user-leave-details/"

    /*GUEST KEYS*/
    const val Coursetype = "coursetype"

    /*saved keys*/
    const val USERDATA = "userdata"
    const val TOKEN = "token"
    const val GUESTUSER = "guestuser"
    const val USERID = "userid"
    const val USER_NAME = "user_name"
    const val USER_EMAIL = "user_email"
    const val TOTALVIDEOS = "total_videos"
    const val FROM = "from"
    const val TYPE = "type"
    const val FRAGMENT_TYPE = "type"
    const val FAQ = "faq"


    /*parms Keys*/
    const val email = "email"
    const val name = "name"
    const val lat = "lat"
    const val lng = "lng"
    const val time = "timing"
    const val password = "password"
    const val type = "type"
    const val leave_type_id = "type_id"
    const val confirm_password = "confirm_password"
    const val rememberMe = "rememberMe"
    const val code = "code"
    const val subject = "subject"
    const val mobile = "mobile"
    const val user_id = "user_id"
    const val leave_category_id = "category_id"
    const val leave_category_id2 = "leave_category_id"
    const val total_leaves = "total_leaves"
    const val available_leaves = "available_leaves"
    const val role_id = "role_id"
    const val reason = "reason"
    const val id = "id"
    const val image = "image"
    const val status = "status"
    const val clockify_key = "clockify_key"
    const val description = "description"
    const val old_password = "old_password"
    const val new_password = "new_password"
    const val start_date = "start_date"
    const val end_date = "end_date"
    const val data = "user_date"
    const val attachment = "attachment"
    const val file = "file"
    const val leaves = "leaves"
}
