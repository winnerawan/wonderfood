package net.winnerawan.wonderfood.ui.home.history;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompleteFragment;
import net.winnerawan.wonderfood.ui.home.history.progress.HistoryProgressFragment;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class HistoryPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private Activity mActivity;

    public HistoryPagerAdapter(FragmentManager fragmentManager, AppCompatActivity activity) {
        super(fragmentManager);
        this.mTabCount=2;
        mActivity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HistoryProgressFragment.newInstance();
            case 1:
                return HistoryCompleteFragment.newInstance();
            default:
                return HistoryProgressFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return mActivity.getString(R.string.on_progress);
            default:
                return mActivity.getString(R.string.completed);
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
