package com.oohyugi.bukasempak.view.home

//import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.utils.MarginItemDecoration
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
                initViewModel()
            }

        })




    }

    private fun initHome() {
        adapter = HomeListAdapterBL(activity!!,mListHome)
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvHome.layoutManager = layoutManager
        rvHome.adapter = adapter
        rvHome.addItemDecoration(MarginItemDecoration(14))
        adapter.notifyDataSetChanged()

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.flashDealMdl
            .observe(this,
                Observer<BaseFlashDealMdl> {
                    mBaseFlashDealMdl = it

                })
        viewModel.menuMdl
            .observe(this,
                Observer<List<MenuItemMdl>> {
                    mListMenu.addAll(it!!)


                })

        viewModel.bannerMdl
            .observe(this,
                Observer<List<BannerMdl>> {
                    mListBanner.addAll(it!!)


                })

        viewModel.isProgress.observe(this,Observer<Boolean>{

                swipeRefresh.isRefreshing = it!!

        })

        viewModel.homeMdl
            .observe(this,
                Observer<List<BLHomeMdl>> {
                    mListHome.clear()
                    mListHome.addAll(it!!)
                    adapter.notifyDataSetChanged()
                    insertItemDataAdapter()

                    Log.e("response","success")
                })


    }

    private fun insertItemDataAdapter() {

        adapter.addItemFlashDeal(mBaseFlashDealMdl)
        adapter.addItemMenu(mListMenu)
        adapter.addItemBanner(mListBanner)
    }
}
