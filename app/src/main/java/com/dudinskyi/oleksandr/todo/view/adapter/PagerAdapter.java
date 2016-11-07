package com.dudinskyi.oleksandr.todo.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.dudinskyi.oleksandr.todo.view.DoneTasksFragment;
import com.dudinskyi.oleksandr.todo.view.PendingTasksFragment;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsCount;

    public PagerAdapter(FragmentManager fm, int tabsCount) {
        super(fm);
        this.tabsCount = tabsCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PendingTasksFragment();
            case 1:
                return new DoneTasksFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
