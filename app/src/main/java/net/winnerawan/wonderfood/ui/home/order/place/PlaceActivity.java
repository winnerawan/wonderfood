package net.winnerawan.wonderfood.ui.home.order.place;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class PlaceActivity extends BaseActivity implements PlaceView {

    @Inject
    PlaceMvpPresenter<PlaceView> mPresenter;

    @Inject
    PSelectPagerAdapter mPagerAdapter;

    @BindView(R.id.viewPagerOrder)
    ViewPager mViewPager;

    @BindView(R.id.tabsmenu)
    PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_up, R.anim.anim_push_up);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.next)
    void finishOrder() {
        mPresenter.updateOrder();
    }

    @Override
    protected void setUp() {
        mPagerAdapter.setCount(2);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
        mTabs.setViewPager(mViewPager);
    }


    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_down, R.anim.anim_push_down);
    }

    public void onBackPressed() {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle(R.string.cancel_order)
                .setMessage(R.string.cancel_order_message)
                .setPositiveButton(android.R.string.ok, view -> {
                    finishAction();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void shoDialogMenuEmpty() {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.red)
                .setTitle(R.string.menu_empty_message)
                .setMessage(R.string.menu_empty_message_0)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void showDialogOrderReceived() {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.red)
                .setTitle(R.string.order_received)
                .setMessage(R.string.order_received_message)
                .setPositiveButton(android.R.string.ok, view -> {
                    finishAction();
                })
                .show();
    }

}
