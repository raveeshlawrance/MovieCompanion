package com.accellion.moviecompanion

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashScreen : AppCompatActivity() {
    private fun getSplashScreenDuration() = 5000L
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        imgMovieLogo.startAnimation(myAnim)
        launchMovieSearchScreen();
        //finish()
    }

    private fun launchMovieSearchScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler(Looper.getMainLooper()).postDelayed({
            val movieSearchIntent = Intent(this, MovieSearchActivity::class.java)
            startActivity(movieSearchIntent)
            finish()
        },splashScreenDuration)


    }
}