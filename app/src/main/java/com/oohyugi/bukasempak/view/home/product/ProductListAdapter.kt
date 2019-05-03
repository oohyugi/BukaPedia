package com.oohyugi.bukasempak.view.home.product

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ItemsMdl
import com.oohyugi.bukasempak.utils.setStrikeStrought

/**
 * Created by oohyugi on 2019-04-25.
 * github: https://github.com/oohyugi
 */
class ProductListAdapter(
    private val context: Context,
    private val list: List<ItemsMdl>,
    val typeItems: String
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvTitle:TextView = itemView.findViewById(R.id.tvTitleProduct)
        val tvPrice:TextView = itemView.findViewById(R.id.tvOriginalPrice)
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
        val item = list[position]

        holder.tvTitle.text = item.title
        holder.tvPrice.text = item.price.toString()
        holder.tvPrice.setStrikeStrought()
        Glide.with(context).load(item.imgUrl).into(holder.ivProduct)
        if (typeItems == "flash_deal"){
            holder.lyStock.visibility = View.VISIBLE
        }else holder.lyStock.visibility = View.GONE

    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {

        private val TAG = ProductListAdapter::class.java.simpleName
    }


}