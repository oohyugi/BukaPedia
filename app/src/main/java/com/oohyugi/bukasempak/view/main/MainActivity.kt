package com.oohyugi.bukasempak.view.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.ViewCompat
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_toolbar.*
import android.view.animation.AnimationUtils.loadAnimation
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import com.oohyugi.bukasempak.utils.replaceFragment
import com.oohyugi.bukasempak.view.home.HomeFragment


class MainActivity : AppCompatActivity() {

    var appBarLayout : AppBarLayout? = null
    private lateinit var viewModel: MainViewModel
    private  var mBaseFlashDealMdl: BaseFlashDealMdl? = null
    lateinit var mListHome:MutableList<BLHomeMdl>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        appBarLayout = findViewById(R.id.appbar)

        this.replaceFragment(HomeFragment.newInstance(),R.id.main_container,"home")

        appBarLayout?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
            if (Math.abs(verticalOffset)-appBarLayout!!.totalScrollRange == 0) {
                //  Collapsed
                //
                if(ly_dana.visibility == View.VISIBLE){
                    etSearchBar.visibility = View.VISIBLE
                    ly_dana.visibility = View.GONE
                    val slideAnimation = loadAnimation(this@MainActivity, R.anim.slide_in)
                    etSearchBar.startAnimation(slideAnimation)

                }


                val elev = 4
                ViewCompat.setElevation(appBarLayout!!,elev.toFloat())


            } else {
                //Expanded
                if (etSearchBar.visibility == View.VISIBLE){
                    etSearchBar.visibility = View.GONE
                    ly_dana.visibility = View.VISIBLE
                }


                val elev = 0
                ViewCompat.setElevation(appBarLayout!!,elev.toFloat())

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds flashDealMdls to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_qrCode -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



}
