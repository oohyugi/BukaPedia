package com.oohyugi.bukasempak.view.home.product

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ItemsMdl
import com.oohyugi.bukasempak.model.ProductMdl
import com.oohyugi.bukasempak.model.ProductsItemMdl
import com.oohyugi.bukasempak.utils.formatCurrency
import com.oohyugi.bukasempak.utils.indonesiaFormat
import com.oohyugi.bukasempak.utils.setStrikeStrought
import com.oohyugi.bukasempak.view.detail.ProductDetailActivity

/**
 * Created by oohyugi on 2019-04-25.
 * github: https://github.com/oohyugi
 */
class ProductListAdapterBL(
    private val context: Context,
    private val list: List<ProductsItemMdl>,
    val typeItems: String
) :
    RecyclerView.Adapter<ProductListAdapterBL.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvTitle:TextView = itemView.findViewById(R.id.tvTitleProduct)
        val tvPrice:TextView = itemView.findViewById(R.id.tvOriginalPrice)
        val tvDiscountPrice:TextView = itemView.findViewById(R.id.tvDiscountPrice)
        val tvDiscountPercent:TextView = itemView.findViewById(R.id.tvDiscountPercent)
        val ivProduct:ImageView = itemView.findViewById(R.id.ivProduct)
        val lyStock:LinearLayout = itemView.findViewById(R.id.ly_stock)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.product_item, parent, false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position].product

        holder.tvTitle.text = item.name
        if (item.deal!=null){
            holder.tvPrice.text = item.deal.originalPrice.formatCurrency()
            holder.tvDiscountPrice.text= item.deal.discountPrice.formatCurrency()
            holder.tvPrice.setStrikeStrought()
            holder.tvDiscountPercent.text  = "${item.deal.percentage}%"
        }else{
            holder.tvPrice.setTextColor(ContextCompat.getColor(context,R.color.black))
            holder.tvPrice.typeface = Typeface.DEFAULT_BOLD
            holder.tvPrice.text = item.price.formatCurrency()
            holder.tvDiscountPrice.visibility = View.GONE
            holder.tvDiscountPercent.visibility = View.GONE

        }
        Glide.with(context).load(item.images.largeUrls?.get(0)).into(holder.ivProduct)
        if (typeItems == "flash_deal"){
            holder.lyStock.visibility = View.VISIBLE
        }else holder.lyStock.visibility = View.GONE

        holder.itemView.setOnClickListener {
            ProductDetailActivity.startThisActivity(context,Gson().toJson(item))
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {

        private val TAG = ProductListAdapterBL::class.java.simpleName
    }


}