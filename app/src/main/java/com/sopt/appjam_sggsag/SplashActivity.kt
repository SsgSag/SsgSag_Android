package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Info.WelcomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if(SharedPreferenceController.checkFirst(this)){
            Handler().apply {
                postDelayed({
                    startActivity<WelcomeActivity>()
                    finish()
                }, 1000)
            }
            SharedPreferenceController.setNotFirst(this)
        } else{
            Handler().apply {
                postDelayed({
                    startActivity<LoginActivity>()
                    finish()
                }, 1000)
            }
        }

    }
}