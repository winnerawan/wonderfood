package net.winnerawan.wonderfood.ui.home.order.delivery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.winnerawan.wonderfood.ui.home.order.delivery.food.DSelectFoodFragment;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class DSelectPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public DSelectPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount=2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DSelectFoodFragment.newInstance();
            case 1:
                return DSelectFoodFragment.newInstance();
            default:
                return DSelectFoodFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Food";
            case 1:
                return "Beverage";
            default:
                return "Food";
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
