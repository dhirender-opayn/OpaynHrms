package com.example.opaynhrms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.opaynhrms.auth.Login
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys

class MainActivity : KotlinBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            //openA(SpeakingTest::class)
            getuserdata()
        }, 1000)
    }

    private fun getuserdata() {
        preferencemanger.getString(Keys.USERDATA).let {
            if (it == null || it.toString().isEmpty()) {
                openA(Login::class,bundle)
                finishAffinity()
            } else {
                bundle.putString(Keys.FROM, "1")
                openA(Home::class, bundle)
                finishAffinity()
            }

        }

    }
}