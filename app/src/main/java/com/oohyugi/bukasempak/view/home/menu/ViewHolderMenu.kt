package com.oohyugi.bukasempak.view.home.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.MenuItemMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration

class ViewHolderMenu(itemView: View,val context: Context) : RecyclerView.ViewHolder(itemView) {
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
    var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
    var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
    var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
    lateinit var menuAdapter: MenuListAdapter
    var mListMenu: MutableList<MenuItemMdl> = mutableListOf()
    fun setItem(list: MutableList<MenuItemMdl>) {
        mListMenu.addAll(list)
        menuAdapter.notifyDataSetChanged()
        tvTitle.text = "E-Voucher, tiket,& investasi"
        tvLoadMore.visibility = View.GONE



    }


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_menu, null, false)

        val rvMenu = view.findViewById<RecyclerView>(R.id.rvMenu)
        menuAdapter = MenuListAdapter(context, mListMenu)
        val layoutManager = GridLayoutManager(context, 4)
        rvMenu.layoutManager = layoutManager as RecyclerView.LayoutManager?
        rvMenu.adapter = menuAdapter
        rvMenu.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_GRID_VERTICAL))
        menuAdapter.notifyDataSetChanged()
        lyContainer.addView(view)

    }

}