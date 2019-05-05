package com.oohyugi.bukasempak.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.oohyugi.bukasempak.R
import android.view.MenuItem


/**
 * Created by oohyugi on 2019-05-05.
 * github: https://github.com/oohyugi
 */
open class BaseActivity : AppCompatActivity() {


    var toolbar:Toolbar? = null
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initToolbar()

    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}