package com.oohyugi.bukasempak.view.home.menu

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.MenuItemMdl
import com.oohyugi.bukasempak.view.home.menu.MenuListAdapter.ViewHolder

/**
 * Created by oohyugi on 2019-05-02.
 * github: https://github.com/oohyugi
 */
class MenuListAdapter(private val context: Context, private val list: List<MenuItemMdl>) :
    RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        var ivIcon : ImageView = itemView.findViewById(R.id.ivIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.menu_item, parent, false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= list[position]

        if (position == list.size-1){
            holder.tvTitle.text = "Lainya"
            holder.ivIcon.setImageResource(R.drawable.ic_apps_black_24dp)
        }else{
            holder.tvTitle.text = item.displayName
            Glide.with(context).load(item.iconUrl).into(holder.ivIcon)
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }

    companion object {

        private val TAG = MenuListAdapter::class.java.simpleName
    }


}