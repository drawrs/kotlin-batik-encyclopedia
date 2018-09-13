package com.khilman.www.batiknusantaraencyclopedia.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khilman.www.batiknusantaraencyclopedia.MainActivity
import com.khilman.www.batiknusantaraencyclopedia.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //TODO: Delay selama 2 detik lalu pindah ke MainActivity
        Timer("splahDisappear", true).schedule(2000){
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }

}
