package com.ebrahimipooria.mymoviesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ebrahimipooria.mymoviesapp.MainActivity
import com.ebrahimipooria.mymoviesapp.R
import java.util.Timer
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer()
        timer.schedule(timerTask {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            timer.cancel()
            finish()
        },3000,1)


    }
}