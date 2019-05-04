package com.oohyugi.bukasempak.view.home

//import android.arch.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import com.oohyugi.bukasempak.utils.PrefHelper
import com.oohyugi.bukasempak.utils.readJSONFromAsset
import com.oohyugi.bukasempak.view.main.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    lateinit var adapter: HomeListAdapterBL
    private  var mBaseFlashDealMdl: BaseFlashDealMdl? = null
    private var mListHome:MutableList<BLHomeMdl> = mutableListOf()
    private var mListMenu:MutableList<MenuItemMdl> = mutableListOf()
    private var mListBanner:MutableList<BannerMdl> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel



        val json = context?.readJSONFromAsset("home.json")
        val listHome: MutableList<HomeMdl>
        val type = object : TypeToken<List<HomeMdl>>() {}.type
        listHome = Gson().fromJson(json, type)

        initHome()
        initViewModel()

        swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                mListMenu.clear()
                mListBanner.clear()
                mListHome.clear()
                adapter.notifyDataSetChanged()
                initViewModel()
            }

        })




    }

    private fun initHome() {
        adapter = HomeListAdapterBL(activity!!,mListHome)
        val layoutManager = LinearLayoutManager(context)
        rvHome.layoutManager = layoutManager
        rvHome.adapter = adapter
        rvHome.addItemDecoration(MarginItemDecoration(14))
        adapter.notifyDataSetChanged()

    }

    private fun initViewModel() {
//        viewModel.flashDealMdl
//            .observe(this,
//                Observer<BaseFlashDealMdl> {
//                    mBaseFlashDealMdl = it
//                    adapter.addItemFlashDeal(mBaseFlashDealMdl)
//                    adapter.notifyDataSetChanged()
//
//                })
//
//
//


        if (PrefHelper.getListBanner(context!!).isEmpty()){

            viewModel.bannerMdl
                .observe(this,
                    Observer<List<BannerMdl>> {
                        PrefHelper.saveListBanner(context!!,it)
                        mListBanner.clear()
                        mListBanner.addAll(it!!)


                    })



        }else{
            mListBanner.addAll(PrefHelper.getListBanner(context!!))
        }

        if (PrefHelper.getListMenu(context!!).isEmpty()){
            swipeRefresh.isRefreshing = true
//            viewModel.isProgress.observe(this,Observer<Boolean>{
//
//                swipeRefresh.isRefreshing = it!!
//
//            })

            viewModel.menuMdl
                .observe(this,
                    Observer<List<MenuItemMdl>> {
                        swipeRefresh.isRefreshing=false
                        PrefHelper.saveListMenu(context!!,it)
                        mListMenu.clear()
                        mListMenu.addAll(it!!)


                    })


        }else{
            swipeRefresh.isRefreshing = false
            mListMenu.addAll(PrefHelper.getListMenu(context!!))
        }
        if (PrefHelper.getListProduct(context!!).isEmpty()){

            viewModel.homeMdl
                .observe(this,
                    Observer<List<BLHomeMdl>> {
                        PrefHelper.saveListProduct(context!!,it)
                        mListHome.clear()
                        mListHome.addAll(it!!)
                        insertItemDataAdapter()
                        adapter.notifyDataSetChanged()

                        Log.e("response","success")
                    })




        }else{
            mListHome.addAll(PrefHelper.getListProduct(context!!))
            insertItemDataAdapter()
            adapter.notifyDataSetChanged()
        }



    }

    private fun insertItemDataAdapter() {
        adapter.addItemMenu(mListMenu)
        adapter.addItemBanner(mListBanner)
    }
}
