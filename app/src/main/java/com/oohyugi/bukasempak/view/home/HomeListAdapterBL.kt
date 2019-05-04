package com.oohyugi.bukasempak.view.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import android.widget.*
import androidx.recyclerview.widget.*
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.view.home.product.ProductListAdapterBL
import com.oohyugi.bukasempak.view.home.banner.ViewHolderBanner
import com.oohyugi.bukasempak.view.home.flash_deal.ViewHolderFlashDeal
import com.oohyugi.bukasempak.view.home.menu.ViewHolderMenu
import com.oohyugi.bukasempak.view.home.product.ViewHolderProductHorizontal
import com.oohyugi.bukasempak.view.home.product.ViewHolderProductVertical
import com.oohyugi.bukasempak.view.home.single_menu.ViewHolderSingleMenu


/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */
class HomeListAdapterBL(private val context: Activity, private val list: List<BLHomeMdl>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val onItemClickListener: OnItemClickListener? = null
    //    private val mList:MutableList<BLHomeMdl> = mutableListOf()
    private var mFlashDealMdl: BaseFlashDealMdl? = null
    private var mListMenu: MutableList<MenuItemMdl> = mutableListOf()
    private var mListBanner: MutableList<BannerMdl> = mutableListOf()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
//        internal var ivIcon: ImageView


    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var view: View?
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            TYPE_DEFAULT -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
            TYPE_PRODUCT_HORIZONTAL -> {
                view = inflater.inflate(R.layout.home_item_decs_left, parent, false)
                viewHolder = ViewHolderProductHorizontal(view,context)
            }
            TYPE_PRODUCT_VERTICAL -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolderProductVertical(context,view)
            }
            TYPE_FLASHDEAL -> {
                view = inflater.inflate(R.layout.home_item_decs_left_flashdeal, parent, false)
                viewHolder = ViewHolderFlashDeal(view,context)
            }
            TYPE_MENU -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolderMenu(view,context)
            }
            TYPE_MENU_SINGLE -> {
                view = inflater.inflate(R.layout.home_item_menu_single, parent, false)
                viewHolder = ViewHolderSingleMenu(view,context)
            }
            TYPE_BANNER -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolderBanner(view,context)
            }
            else -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
        }

        return viewHolder


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            TYPE_DEFAULT -> {
                val vh = holder as ViewHolder
                configureViewHolder(vh, position)
            }
            TYPE_PRODUCT_HORIZONTAL -> {
                val vh = holder as ViewHolderProductHorizontal
                vh.setItem(list[position-4])

            }
        TYPE_PRODUCT_VERTICAL -> {
            val vh = holder as ViewHolderProductVertical
            vh.setItem(list[position-4])

        }

            TYPE_MENU -> {
                val vh = holder as ViewHolderMenu
                vh.setItem(mListMenu)
//                configureViewHolderMenu(vh, position)
            }
            TYPE_MENU_SINGLE -> {
                val vh = holder as ViewHolderSingleMenu
//                vh.setItem(list[position])
            }
            TYPE_BANNER -> {
                val vh = holder as ViewHolderBanner

                vh.setItem(mListBanner)
            }
        }


    }

    private fun configureViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position - 4]
        holder.tvTitle.text = item.title

        if (item.type.contains("Products Section")) {
            initLayoutProduct(holder, item)
        }

    }

    lateinit var productListAdapter: ProductListAdapterBL

    private fun initLayoutProduct(holder: ViewHolder, item: BLHomeMdl) {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product_horizontal, null, false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        productListAdapter = ProductListAdapterBL(context, item.products!!, "default")
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
        rvProduct.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))
        productListAdapter.notifyDataSetChanged()

        holder.lyContainer.addView(view)
    }
//

    override fun getItemViewType(position: Int): Int {


        return if (position == 3) {
            TYPE_FLASHDEAL
        } else if (position == 2) {
            TYPE_MENU
        } else if (position == 1) {
            TYPE_BANNER
        } else if (position == 0) {
            TYPE_MENU_SINGLE
        } else {
            if (list[position - 4].type.contains("Popular Products Section", true)) {
                TYPE_PRODUCT_HORIZONTAL

            }else if (list[position-4].type.contains("Recently",true)){
                TYPE_PRODUCT_VERTICAL
            }
            else TYPE_DEFAULT
        }

    }


    override fun getItemCount(): Int {

        return list.size + 4
    }



    fun addItemFlashDeal(mBaseFlashDealMdl: BaseFlashDealMdl?) {
        mFlashDealMdl = mBaseFlashDealMdl


    }

    fun addItemMenu(list: MutableList<MenuItemMdl>) {
        var i = 0
        var sortedList = list.sortedWith(compareBy { it.ordering })
        mListMenu.addAll(sortedList)


    }

    fun addItemBanner(list: MutableList<BannerMdl>) {
        mListBanner.clear()
        mListBanner.addAll(list)
    }


    interface OnItemClickListener {

    }

    companion object {

        private val TYPE_DEFAULT = 4
        private val TYPE_PRODUCT_HORIZONTAL = 5
        private val TYPE_PRODUCT_VERTICAL = 6
        private val TYPE_FLASHDEAL = 3
        private val TYPE_MENU = 2
        private val TYPE_BANNER = 1
        private val TYPE_MENU_SINGLE = 0
    }
}


