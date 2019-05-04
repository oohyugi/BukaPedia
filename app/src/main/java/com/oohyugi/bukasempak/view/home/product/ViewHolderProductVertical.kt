package com.oohyugi.bukasempak.view.home.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.ProductsItemMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration

class ViewHolderProductVertical( val context: Context?, itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var productListAdapter: ProductVerticalListAdapterBL
    var mListProducts: MutableList<ProductsItemMdl> = mutableListOf()
    var data: BLHomeMdl? = null
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
    var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
    var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
    var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
    fun setItem(item: BLHomeMdl) {
        lyHome.visibility = View.VISIBLE
        data = item
        tvTitle.text = data?.title

        mListProducts.clear()
        mListProducts.addAll(data?.products!!)
        productListAdapter.notifyDataSetChanged()



    }

    private fun initLayoutProducts() {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product_vertical, null, false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = GridLayoutManager(context,2)
//        layoutManager.reverseLayout = true
        productListAdapter = ProductVerticalListAdapterBL(context!!, mListProducts, "default")
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
//        rvProduct.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_GRID_VERTICAL))
        productListAdapter.notifyDataSetChanged()
        lyContainer.addView(view)
    }

    init {
        initLayoutProducts()
    }
}
