package com.oohyugi.bukasempak.utils.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.oohyugi.bukasempak.R;

/**
 * Created by oohyugi on 2019-04-27.
 * github: https://github.com/oohyugi
 */
public class CircularViewPager extends ViewPager {
    private final CircularViewPagerListener mListener;
    private int mPageCount;

    public CircularViewPager(Context context) {
        this(context, null);
    }

    public CircularViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        mListener = new CircularViewPagerListener();
        setOverScrollMode(OVER_SCROLL_NEVER);
        setOffscreenPageLimit(1);
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.CircularViewPager, 0, 0);
        mPageCount = a.getInteger(R.styleable.CircularViewPager_pageCount, 0);
        a.recycle();
    }

    public void setFragmentPagerAdapter(FragmentManager manager, final GetFragmentItemListener listener) {
        CircularFragmentPagerAdapter adapter = new CircularFragmentPagerAdapter(manager, mPageCount) {
            @Override
            protected Fragment getFragment(int position) {
                return listener.getFragment(position);
            }
        };
        setAdapter(adapter);
    }

    public void setLayoutPagerAdapter(final GetLayoutItemListener listener) {
        LayoutPagerAdapter adapter = new LayoutPagerAdapter(mPageCount) {
            @Override
            protected int getLayout(int position) {
                return listener.getLayout(position);
            }
        };
        setAdapter(adapter);
    }

    public void setPageCount(int count) {
        mPageCount = count;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        addOnPageChangeListener(mListener);
        setCurrentItem(1);
    }

    public interface GetFragmentItemListener {
        Fragment getFragment(int position);
    }

    public interface GetLayoutItemListener {
        int getLayout(int position);
    }


    private class CircularViewPagerListener implements OnPageChangeListener {
        private static final int DELAY = 200;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    loopCurrentItem(position);
                }
            }, DELAY);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        private void loopCurrentItem(int position) {
            int pageCount = getAdapter().getCount();
            if (position == pageCount - 1) {
                setCurrentItem(1, false);
            } else if (position == 0) {
                setCurrentItem(pageCount - 2, false);
            }
        }
    }
}
