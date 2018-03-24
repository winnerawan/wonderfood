package net.winnerawan.wonderfood.ui.home.order.place;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageFragment;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodFragment;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeverageFragment;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodFragment;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class PSelectPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public PSelectPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount=2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PSelectFoodFragment.newInstance();
            case 1:
                return PSelectBeverageFragment.newInstance();
            default:
                return PSelectFoodFragment.newInstance();
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
