package com.evanemran.kickoff

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.evanemran.kickoff.constants.SharedPrefs
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    var topAnim: Animation? = null
    var bottomAnim:Animation? = null
    var verifidBannerSplash: ImageView? = null
    var leadsBanner: RelativeLayout? = null
    var versionNoSplash: TextView? = null
    private val sleep: Long = 3000
    var mThread: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        verifidBannerSplash?.setAnimation(topAnim)
        leadsBanner?.setAnimation(bottomAnim)
        versionNoSplash?.setAnimation(bottomAnim)

        startMain()

        try {
            val pInfo = applicationContext.packageManager.getPackageInfo(
                applicationContext.packageName, 0
            )
            versionNoSplash?.setText("Version: " + pInfo.versionName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun startMain() {
        mThread = object : Thread() {
            override fun run() {
                try {
//                    sleep(sleep)
                    if(SharedPrefs(this@SplashActivity).getToken().isEmpty()){
                        startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
                    }
                    else {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    finish()
                }
            }
        }
        mThread?.start()
    }

    override fun onPause() {
        super.onPause()
        if (mThread != null) mThread!!.interrupt()
    }
}