package com.oohyugi.bukasempak.view.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.base.BaseActivity
import com.oohyugi.bukasempak.model.ProductMdl
import com.oohyugi.bukasempak.utils.indonesiaFormat
import com.oohyugi.bukasempak.utils.setStrikeStrought
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import android.graphics.PorterDuff
import android.os.Build
import androidx.annotation.RequiresApi


class ProductDetailActivity : AppCompatActivity() {


    companion object{
        const val EXTRA_DATA = "productData"
        fun startThisActivity(context: Context,data:String){
            val intent = Intent(context,ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_DATA,data)
            context.startActivity(intent)
        }
    }

    private lateinit var mData:ProductMdl
    lateinit var imgProductPagerAdapter: ImgProductPagerAdapter
    var mItem: Menu? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val json  = intent.getStringExtra(EXTRA_DATA)

        mData = Gson().fromJson(json,ProductMdl::class.java)

        setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appBarLayout:AppBarLayout = findViewById(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
            if (Math.abs(verticalOffset)-appBarLayout.totalScrollRange == 0) {
                //  Collapsed
               toolbar?.setBackgroundColor(ContextCompat.getColor(this,R.color.white))

                val elev = 4
                ViewCompat.setElevation(appBarLayout,elev.toFloat())
                mItem?.findItem(R.id.action_love)?.setIcon(R.drawable.ic_favorite_black_24dp)
                mItem?.findItem(R.id.action_cart)?.setIcon(R.drawable.ic_shopping_cart_black_24dp)
//                mItem?.findItem(android.R.id.home)?.setIcon(R.drawable.ic_arrow_back_black_24dp)
//                toolbar?.context?.setTheme(R.style.ThemeOverlay_AppCompat_Light)
                toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.softBlack), PorterDuff.Mode.SRC_ATOP);

                toolbar.overflowIcon =resources.getDrawable( R.drawable.ic_more_vert_black_24dp)

            } else {
                //Expanded
                toolbar?.setBackgroundColor(ContextCompat.getColor(this,R.color.transparantBlack))


                val elev = 0
                ViewCompat.setElevation(appBarLayout,elev.toFloat())
                mItem?.findItem(R.id.action_love)?.setIcon(R.drawable.ic_favorite_white_24dp)
                mItem?.findItem(R.id.action_cart)?.setIcon(R.drawable.ic_shopping_cart_white_24dp)
                toolbar.overflowIcon =resources.getDrawable( R.drawable.ic_more_vert_white_24dp)
                toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

//                mItem?.findItem(android.R.id.home)?.setIcon(R.drawable.ic_arrow_back_white_24dp)
//                toolbar?.context?.setTheme(android.R.style.ThemeOverlay_Material_Dark)

            }


//            val offset = Math.abs(verticalOffset)  / appBarLayout.totalScrollRange
//            toolbar.background.alpha = (offset * 255)

//                .setColorFilter(ContextCompat.getColor(this@ActivityHome, R.color.yourcolor), PorterDuff.Mode.SRC_IN)

        })


        imgProductPagerAdapter = ImgProductPagerAdapter(supportFragmentManager, mData.images.largeUrls!!)
        vpImage.adapter = imgProductPagerAdapter
        rating_product.rating = mData.rating.averageRate.toFloat()
        tv_title.text = mData.name
        tv_ulasan.text = "${mData.rating.userCount} ulasan"

        tv_normal_price.text = mData.price!!.toDouble().indonesiaFormat()

        if (mData.deal!=null){
            tv_discount_percent.text = "${mData.deal?.percentage}%"
            tv_price_discount.text = mData.deal?.discountPrice!!.toDouble().indonesiaFormat()
            tv_normal_price.setStrikeStrought()
        }else{
            tv_discount_percent.visibility=View.GONE
            tv_price_discount.visibility = View.INVISIBLE
        }

        tv_condition.text = mData.condition
        tv_stock.text = "${mData.stock}"
        tv_terjual.text = "${mData.stats.soldCount}"
        tv_desc.text = Html.fromHtml(mData.description)
        btnMoreDesc.setOnClickListener {
            val bottomsheet = BottomSheetAllDescription.newInstance(Gson().toJson(mData))
            bottomsheet.show(supportFragmentManager,"all_desc")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mItem = menu
        menuInflater.inflate(R.menu.menu_detail,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
