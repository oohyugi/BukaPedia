package com.oohyugi.bukasempak.utils.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oohyugi on 2019-04-27.
 * github: https://github.com/oohyugi
 */
abstract class LayoutPagerAdapter extends PagerAdapter {
    private final List<Integer> mPageList;

    public LayoutPagerAdapter(int count) {
        mPageList = new ArrayList<>();
        mPageList.add(count + 1);
        for (int index = 0; index < count; index++) {
            mPageList.add(index + 1);
        }
        mPageList.add(0);
    }

    @Override
    public int getCount() {
        return mPageList.size() > 2 ? mPageList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        int layout;
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (position == mPageList.size() - 1) {
            layout = getLayout(0);
        } else if (position == 0) {
            layout = getLayout(mPageList.size() - 3);
        } else {
            layout = getLayout(mPageList.get(position) - 1);
        }
        View view = inflater.inflate(layout, null);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    protected abstract int getLayout(int position);
}

