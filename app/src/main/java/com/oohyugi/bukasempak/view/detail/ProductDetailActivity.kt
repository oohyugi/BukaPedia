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
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ProductMdl
import com.oohyugi.bukasempak.utils.indonesiaFormat
import com.oohyugi.bukasempak.utils.setStrikeStrought
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import android.graphics.PorterDuff
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.oohyugi.bukasempak.model.BaseMdl
import com.oohyugi.bukasempak.model.ReviewProductMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import com.oohyugi.bukasempak.utils.PrefHelper
import com.oohyugi.bukasempak.view.home.product.ProductSimilarListAdapterBL
import kotlinx.android.synthetic.main.detail_product_bottomsheet.*
import kotlinx.android.synthetic.main.detail_product_content.*


class ProductDetailActivity : AppCompatActivity() {


    companion object{
        const val EXTRA_DATA = "productData"
        fun startThisActivity(context: Context,data:String){
            val intent = Intent(context,ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_DATA,data)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var mData:ProductMdl
    lateinit var imgProductPagerAdapter: ImgProductPagerAdapter
    var mItem: Menu? = null
    private var mBottomSheetBehavior: BottomSheetBehavior<RelativeLayout>? = null
    private var hasmapSpecs: MutableMap<String, Any> = mutableMapOf()
    private var mListReviewProductMdl:MutableList<ReviewProductMdl> = mutableListOf()
    private var mListSimilarProductMdl:MutableList<ProductMdl> = mutableListOf()
    lateinit var reviewAdapter: ProductReviewAdapter
    lateinit var similarAdapter: ProductSimilarListAdapterBL
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val json  = intent.getStringExtra(EXTRA_DATA)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        mData = Gson().fromJson(json,ProductMdl::class.java)

        setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appBarLayout:AppBarLayout = findViewById(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
            if (Math.abs(verticalOffset)-appBarLayout.totalScrollRange == 0) {
                //  Collapsed
               toolbar?.setBackgroundColor(ContextCompat.getColor(this,R.color.white))

//                val elev = 4
//                ViewCompat.setElevation(appBarLayout,elev.toFloat())
                mItem?.findItem(R.id.action_love)?.setIcon(R.drawable.ic_favorite_black_24dp)
                mItem?.findItem(R.id.action_cart)?.setIcon(R.drawable.ic_shopping_cart_black_24dp)
//                mItem?.findItem(android.R.id.home)?.setIcon(R.drawable.ic_arrow_back_black_24dp)
//                toolbar?.context?.setTheme(R.style.ThemeOverlay_AppCompat_Light)
                toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.softBlack), PorterDuff.Mode.SRC_ATOP);

                toolbar.overflowIcon =resources.getDrawable( R.drawable.ic_more_vert_black_24dp)

            } else {
                //Expanded
                toolbar?.setBackgroundColor(ContextCompat.getColor(this,R.color.transparantBlack))


//                val elev = 0
//                ViewCompat.setElevation(appBarLayout,elev.toFloat())
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
        Glide.with(this).load(mData.store.avatarUrl).into(iv_user)
        tv_user.text = mData.store.name
        tv_loc.text = mData.store.address.city
        Glide.with(this).load(mData.store.level.imageUrl).into(iv_level)
        tv_level.text =mData.store.level.name
        tv_total_feedback.text = "100% (${mData.store.reviews.positive} feedback)"
        tv_waktu_kirim.text = "Waktu kirim pesanan ± ${mData.store.deliveryTime}"
        tv_rating.text = mData.rating.averageRate.toString()

        initReview()
        initSimilar()

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        view_touch.setOnClickListener {
            showCloseBottomSheet()
        }
        setupBottomSheetData()
        mBottomSheetBehavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> finish()
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        view_touch.visibility = View.GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {


                Log.e("slodeOffset",slideOffset.toString())
                view_touch.visibility = View.VISIBLE
                view_touch.alpha = slideOffset

            }
        })
        btnMoreDesc.setOnClickListener {
//            val bottomsheet = BottomSheetAllDescription.newInstance(Gson().toJson(mData))
//            bottomsheet.show(supportFragmentManager,"all_desc")
           showCloseBottomSheet()
        }

    }

    private fun initSimilar() {
        similarAdapter = ProductSimilarListAdapterBL(this,mListSimilarProductMdl)
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvSimilar.addItemDecoration(MarginItemDecoration(14,MarginItemDecoration.TYPE_HORIZONTAL))
        rvSimilar.layoutManager = layoutManager
        rvSimilar.adapter = similarAdapter
        viewModel.similarProductCall(PrefHelper.getToken(this)!!,mData.id)
        viewModel.mListProductMdl.observe(this,
            Observer<BaseMdl<List<ProductMdl>>> {
                Log.e("reviewProduct",Gson().toJson(it))
                mListSimilarProductMdl.addAll(it.data)
                similarAdapter.notifyDataSetChanged()
            })

    }

    private fun initReview() {

        progress_review.visibility = View.VISIBLE
        reviewAdapter = ProductReviewAdapter(this,mListReviewProductMdl)
        val layoutManager = LinearLayoutManager(this)
        rvReview.layoutManager = layoutManager
        rvReview.adapter = reviewAdapter

//        val dividerItemDecoration =  DividerItemDecoration(this,
//        layoutManager.orientation)
//        rvReview.addItemDecoration(dividerItemDecoration)
        viewModel.reviewProductCall(PrefHelper.getToken(this)!!,mData.id,3)
        viewModel.mListReviewMdl.observe(this,
            Observer<BaseMdl<List<ReviewProductMdl>>> {
                Log.e("reviewProduct",Gson().toJson(it))
                mListReviewProductMdl.addAll(it.data)
                btn_see_all_review.text = "Lihat Semua Ulasan (${it.meta!!.total})"
                reviewAdapter.notifyDataSetChanged()
                progress_review.visibility = View.GONE
                content_review.visibility = View.VISIBLE
            })


    }

    private fun setupBottomSheetData() {

        tv_stock_sheet.text = "${mData.stock}"
        tv_terjual_sheet.text = "${mData.stats.soldCount}"

        tv_desc_sheet.text = Html.fromHtml(mData.description)

        btnClose.setOnClickListener {
            showCloseBottomSheet()
        }
        val containerSpec = findViewById<LinearLayout>(R.id.container_spec)

//

        try {

            hasmapSpecs["Kategori"] = mData.category.name
            hasmapSpecs["Kondisi"] = mData.condition
            hasmapSpecs.putAll(mData.specs!!)
            Log.e("specs", hasmapSpecs.toString())
            var valueArray: MutableList<Any> = mutableListOf()
            var valueShow = ""
            var valueString = ""
            var keyShow = ""
            var i = 0

            hasmapSpecs
            if (hasmapSpecs.isNotEmpty()) {
                ly_specdesc.visibility = View.VISIBLE
                for (itemSpecs in hasmapSpecs) {

                    i++

                    valueString = itemSpecs.value.toString().replace("[", "").replace("]", "")

                    valueShow = if (valueString.isNotEmpty()) valueString
                    else "-"

                    keyShow = itemSpecs.key

                    val viewSpec =
                        LayoutInflater.from(this).inflate(R.layout.detail_product_item_spesifikasi, null, false)

                    val tvName = viewSpec.findViewById<TextView>(R.id.tv_name)
                    val tvValue = viewSpec.findViewById<TextView>(R.id.tv_value)
                    val lySpec = viewSpec.findViewById<LinearLayout>(R.id.ly_spec)

                    tvName.text = keyShow.replace("_", " ").replace("-", " ").capitalize()
                    if (keyShow.equals("Kondisi",true)){
                        tvValue.background =resources.getDrawable(R.drawable.shape_rounded_accent_10dp)
                        tvValue.setTextColor(ContextCompat.getColor(this,R.color.white))
                        tvValue.setPadding(8,3,8,3)
                    }
                    tvValue.text = valueShow.capitalize()
                    if (i % 2 == 0) {
                        lySpec.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    } else {
                        lySpec.setBackgroundColor(ContextCompat.getColor(this, R.color.whiteSmoke))
                    }

                    Log.e("specs", itemSpecs.toString())
                    containerSpec.addView(viewSpec)
                }


            } else {
                ly_specdesc.visibility = View.GONE
            }

        } catch (e: Exception) {

        }

    }

    private fun showCloseBottomSheet() {
        if((mBottomSheetBehavior)!!.state != BottomSheetBehavior.STATE_EXPANDED) {
            (mBottomSheetBehavior)!!.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        else {
            (mBottomSheetBehavior)!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
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
