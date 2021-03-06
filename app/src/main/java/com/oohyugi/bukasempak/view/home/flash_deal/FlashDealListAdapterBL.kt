package com.oohyugi.bukasempak.view.home.flash_deal

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.utils.formatCurrency
import com.oohyugi.bukasempak.utils.setStrikeStrought
import com.oohyugi.bukasempak.view.detail.ProductDetailActivity

/**
 * Created by oohyugi on 2019-04-25.
 * github: https://github.com/oohyugi
 */
class FlashDealListAdapterBL(
    private val context: Context,
    private val list: List<ItemsFlashDealMdl>
) :
    RecyclerView.Adapter<FlashDealListAdapterBL.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitleProduct)
        val tvPrice: TextView = itemView.findViewById(R.id.tvOriginalPrice)
        val tvDiscountPrice: TextView = itemView.findViewById(R.id.tvDiscountPrice)
        val tvDiscountPercent: TextView = itemView.findViewById(R.id.tvDiscountPercent)
        val tvStock: TextView = itemView.findViewById(R.id.tvStock)
        val progress: ProgressBar = itemView.findViewById(R.id.progress)
        val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)
        val lyStock: LinearLayout = itemView.findViewById(R.id.ly_stock)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.flashdeal_item, parent, false)


        return ViewHolder(view)
    }

    var terbeli: Int = 0
    var soldPercent: Double = 0.0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvTitle.text = item.name

        holder.tvPrice.text = item.originalPrice.formatCurrency()
        holder.tvDiscountPrice.text = item.discountPrice.formatCurrency()
        holder.tvPrice.setStrikeStrought()
        holder.tvDiscountPercent.text = "${item.percentage}%"
        Glide.with(context).load(item.images?.get(0)).into(holder.ivProduct)

        terbeli= item.flashDealStock-item.stock
        soldPercent = ((terbeli.toDouble()/item.flashDealStock.toDouble())*100)
        holder.progress.progress = soldPercent.toInt()
        if (item.stock == 0) {
            holder.tvStock.text = "Habis"
        } else {
            holder.tvStock.text = "${item.stock} Tersedia"
        }

        holder.lyStock.visibility = View.VISIBLE
//        holder.itemView.setOnClickListener {
//            ProductDetailActivity.startThisActivity(context, Gson().toJson(item))
//        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {

        private val TAG = FlashDealListAdapterBL::class.java.simpleName
    }


}