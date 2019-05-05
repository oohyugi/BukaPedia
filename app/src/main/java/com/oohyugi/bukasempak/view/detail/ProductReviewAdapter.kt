package com.oohyugi.bukasempak.view.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ReviewProductMdl
import com.oohyugi.bukasempak.view.detail.ProductReviewAdapter.ViewHolder

/**
 * Created by oohyugi on 2019-05-05.
 * github: https://github.com/oohyugi
 */
class ProductReviewAdapter(private val context: Context, private val list: List<ReviewProductMdl>) :
    RecyclerView.Adapter<ProductReviewAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUser:TextView = itemView.findViewById(R.id.tv_user)
        var tvDate:TextView = itemView.findViewById(R.id.tv_date)
        var tvTitle:TextView = itemView.findViewById(R.id.tv_title)
        var tvSubTitle:TextView = itemView.findViewById(R.id.tv_subtitle)
        var tvLike:TextView = itemView.findViewById(R.id.tv_like)
        var ivUser:ImageView = itemView.findViewById(R.id.iv_user)
        var rating:RatingBar = itemView.findViewById(R.id.rating_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.product_review_item, parent, false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        Glide.with(context).load(item.sender.avatarUrl).into(holder.ivUser)
        holder.tvUser.text = item.sender.name
        holder.tvTitle.text = item.review.title
        holder.tvSubTitle.text = item.review.content
        holder.tvLike.text = "${item.votes.positiveVotes} orang"
        holder.rating.rating = item.review.rate.toFloat()
        holder.rating.isClickable = false
    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {

        private val TAG = ProductReviewAdapter::class.java.simpleName
    }


}