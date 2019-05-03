package com.oohyugi.bukasempak.view.home.slider;

/**
 * Created by oohyugi on 2019-04-25.
 * github: https://github.com/oohyugi
 */
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class CyclicPagesAdapter extends FragmentStatePagerAdapter {

    public CyclicPagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return _fragments.get(position).createInstance();
    }

    @Override
    public int getCount() {
        return _fragments.size();
    }

    public interface FragmentCreator {
        Fragment createInstance();
    }

    public void clear() {
        _fragments.clear();
    }

    public void add(FragmentCreator fragmentCreator) {
        _fragments.add(fragmentCreator);
    }

    public void finishAdding() {
        if (_fragments.isEmpty()) {
            return;
        }

        ArrayList<FragmentCreator> arrayList = new ArrayList<>(Arrays.asList(new FragmentCreator[MAX_PAGES]));
        arrayList.addAll(MAX_PAGES / 2, _fragments);

        int mrPointer = _fragments.size() - 1;
        for (int i = MAX_PAGES / 2 - 1; i > -1; --i) {
            arrayList.set(i, _fragments.get(mrPointer));
            --mrPointer;
            if (mrPointer < 0) {
                mrPointer = _fragments.size() - 1;
            }
        }

        mrPointer = 0;
        for (int i = MAX_PAGES / 2 + _fragments.size(); i < arrayList.size(); ++i) {
            arrayList.set(i, _fragments.get(mrPointer));
            ++mrPointer;
            if (mrPointer >= _fragments.size()) {
                mrPointer = 0;
            }
        }
        _fragmentsRaw = _fragments;
        _fragments = arrayList;
    }

    public int getStartIndex() {
        if (_fragments.isEmpty()) {
            return 0;
        }
        return MAX_PAGES / 2;
    }

    public int getRealPagesCount() {
        if (_fragmentsRaw == null) {
            return 0;
        }
        return _fragmentsRaw.size();
    }

    public int getRealPagePosition(int index) {
        if (_fragments.isEmpty() || _fragmentsRaw == null) {
            return 0;
        }
        final FragmentCreator fragmentCreator = _fragments.get(index);
        for (int i = 0; i < _fragmentsRaw.size(); ++i) {
            if (fragmentCreator == _fragmentsRaw.get(i)) {
                return i;
            }
        }
        //Fail...
        return 0;
    }

    private static final int MAX_PAGES = 500;
    public ArrayList<FragmentCreator> _fragments = new ArrayList<>();
    @Nullable
    public ArrayList<FragmentCreator> _fragmentsRaw;
}
