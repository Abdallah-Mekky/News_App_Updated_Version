package com.task1.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task1.news.newVersion.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)

        finish()
    }
}