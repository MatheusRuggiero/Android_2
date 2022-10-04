package br.com.tecnomotor.marvin.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.config.AppConfig
import br.com.tecnomotor.marvin.view.MainActivity
import br.com.tecnomotor.marvin.utils.CountUpTimer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private companion object {
        val timeLoad: Long = 2000
    }

    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
//        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_splash_screen)

        progress = findViewById(R.id.pb_splash_progress)
        progress.progress = 0
        var positionProgress = 0

        val configApp = AppConfig.getInstance(applicationContext)
        if (!configApp.firstExecution())
            configApp.setFirstExecution(true)

        val count = object: CountUpTimer(1, timeLoad) {
            override fun onTick(millisFinished: Long) {
                positionProgress = ((millisFinished * 100) / timeLoad).toInt()
                progress.progress = positionProgress
            }

            override fun onFinish() {
                nextActivity()
            }
        }
        count.start()

    }

    @InternalCoroutinesApi
    fun nextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}