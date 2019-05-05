package com.oohyugi.bukasempak.view.detail

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ProductMdl
import kotlinx.android.synthetic.main.detail_product_all_description.*





/**
 * Created by oohyugi on 2019-05-05.
 * github: https://github.com/oohyugi
 */

private const val ARG_PARAM = "param"

class BottomSheetAllDescription: BottomSheetDialogFragment() {
    private var json: String?= null
    private lateinit var mData: ProductMdl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            json = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // get the views and attach the listener

        return inflater.inflate(
            R.layout.detail_product_all_description, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mData = Gson().fromJson(json,ProductMdl::class.java)

        tv_stock.text = "${mData.stock}"
        tv_terjual.text = "${mData.stats.soldCount}"

        tv_desc.text = Html.fromHtml(mData.description)

        val containerSpec = view.findViewById<LinearLayout>(R.id.container_spec)

//

            try {

                for (i in 1..7) {
                    val viewSpec =
                        LayoutInflater.from(context).inflate(R.layout.detail_product_item_spesifikasi, null, false)

//        val viewSpec = inflater.inflate(R.layout.detail_product_item_spesifikasi,null,false)
                    val tvName = viewSpec.findViewById<TextView>(R.id.tv_name)
                    val tvValue = viewSpec.findViewById<TextView>(R.id.tv_value)
                    val lySpec = viewSpec.findViewById<LinearLayout>(R.id.ly_spec)

                    tvName.text = "name $i"
                    tvValue.text = "value $i"
                    if (i % 2 == 0) {
                        lySpec.setBackgroundColor(ContextCompat.getColor(context!!, R.color.white))
                    } else {
                        lySpec.setBackgroundColor(ContextCompat.getColor(context!!, R.color.gainsboro))
                    }

                    containerSpec.addView(viewSpec)
                }
            }catch (e : Exception){

            }



//        }



    }

    companion object {
        @JvmStatic
        fun newInstance(data: String) =
            BottomSheetAllDescription().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, data)
                }
            }
    }

}

