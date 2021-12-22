package com.example.opaynhrms.applications

import android.app.Application
import android.os.Bundle
import com.example.opaynhrms.di.appModules
import org.koin.android.ext.android.startKoin

class HrmsApplication : Application()  {

//     lateinit var firebaseAnalytics: FirebaseAnalytics
    val bundle = Bundle()


    override fun onCreate()
    {
        super.onCreate()
        myApp = this
        val modulelist= appModules
//        FirebaseApp.initializeApp(myApp!!)
//       firebaseAnalytics = FirebaseAnalytics.getInstance(myApp!!)

        startKoin(this,modulelist)
     }
    companion object{
        var myApp: HrmsApplication? = null
    }


}