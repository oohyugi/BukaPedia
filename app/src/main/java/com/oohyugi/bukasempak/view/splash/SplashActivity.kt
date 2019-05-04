package com.oohyugi.bukasempak.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.utils.PrefHelper
import com.oohyugi.bukasempak.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)


//        viewModel.isProgress.observe(this,Observer<Boolean>{
//
//            if (it!!){
//                progress.visibility = View.VISIBLE
//            }else{
//                progress.visibility = View.GONE
//                val intent = Intent(this,MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//
//        })
        progress.visibility = View.VISIBLE
        viewModel.mToken.observe(this, Observer<String> {
            PrefHelper.saveToken(this,it)
//            progress.visibility = View.GONE
            gotoMain()
        })
//        if (PrefHelper.getToken(this)==null){
//            viewModel.mToken.observe(this, Observer<String> {
//                PrefHelper.saveToken(this,it)
//                progress.visibility = View.GONE
//                gotoMain()
//            })
//        }else{
//            gotoMain()
//        }



    }

    private fun gotoMain() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
