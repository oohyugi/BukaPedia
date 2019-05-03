package com.oohyugi.bukasempak.view.home.banner

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BannerMdl

/**
 * Created by oohyugi on 2019-05-03.
 * github: https://github.com/oohyugi
 */
class BannerAdapter(private val context: Context, private val list: List<BannerMdl>) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivBanner: ImageView = itemView.findViewById(R.id.ivBanner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.banner_item, parent, false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mPos =  position % list.size
        val item= list[mPos]
        Glide.with(context).load(item.image.newMobileUrl).into(holder.ivBanner)

    }


    override fun getItemCount(): Int {
        return list.size * 2
    }

    companion object {

        private val TAG = BannerAdapter::class.java.simpleName
    }


}