package com.oohyugi.bukasempak.view.home.menu

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.widget.DialogTitle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.TextView
import com.oohyugi.bukasempak.R
import androidx.core.content.ContextCompat.getSystemService
import java.security.AccessController.getContext


/**
 * Created by oohyugi on 2019-05-02.
 * github: https://github.com/oohyugi
 */
class MenuAdapter(val context: Context,val textViewResourceId: Int, val title:List<String>) : BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View? {
        var view = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(textViewResourceId, null)
        val tvTitle = view?.findViewById<TextView>(R.id.tvTitle)
        tvTitle?.text = title[position]
        return view

    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return title.size
    }

}