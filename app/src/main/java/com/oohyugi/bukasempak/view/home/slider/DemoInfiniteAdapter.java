package com.oohyugi.bukasempak.view.home.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.oohyugi.bukasempak.R;
import com.oohyugi.bukasempak.model.BannerMdl;
import com.oohyugi.bukasempak.utils.LoopingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oohyugi on 2019-05-02.
 * github: https://github.com/oohyugi
 */
public class DemoInfiniteAdapter extends LoopingPagerAdapter<BannerMdl> {

    public DemoInfiniteAdapter(Context context, List<BannerMdl> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    //This method will be triggered if the item View has not been inflated before.
    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.slider_fragment, container, false);
    }

    //Bind your data with your item View here.
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
//        convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
        ImageView img = convertView.findViewById(R.id.imgSlider);
        Glide.with(context).load(itemList.get(listPosition).getImage().getNewMobileUrl()).into(img);
    }
}
