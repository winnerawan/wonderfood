package net.winnerawan.wonderfood.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.winnerawan.wonderfood.ui.home.help.HelpFragment;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageFragment;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExploreFragment;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodFragment;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class HelpPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public HelpPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount=1;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HelpFragment.newInstance();
            default:
                return HelpFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "";
            default:
                return "";
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
