package net.winnerawan.wonderfood.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.home.account.AccountActivity;
import net.winnerawan.wonderfood.ui.home.booking.BookingActivity;
import net.winnerawan.wonderfood.ui.home.help.HelpFragment;
import net.winnerawan.wonderfood.ui.home.history.HistoryPagerAdapter;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompleteFragment;
import net.winnerawan.wonderfood.ui.home.order.delivery.location.LocationActivity;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodeActivity;
import net.winnerawan.wonderfood.ui.home.search.SearchActivity;

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
public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    HomeMvpPresenter<HomeView> mPresenter;

    @Inject
    HomePagerAdapter mHomePagerAdapter;
    
    @Inject
    HelpPagerAdapter mHelpPagerAdapter;


    @Inject
    HistoryPagerAdapter mHistoryPagerAdapter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @BindView(R.id.btn_home)
    ImageView btnHome;
    @BindView(R.id.btn_history)
    ImageView btnHistory;
    @BindView(R.id.btn_help)
    ImageView btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
        mTabs.setViewPager(mViewPager);
    }

    @OnClick(R.id.f_btn_home)
    void home() {
        setUp();
        mHomePagerAdapter.notifyDataSetChanged();
        mTabs.notifyDataSetChanged();
        setNonActive();
        setCurentItemHomePager(0);
        btnHome.setImageResource(R.drawable.ic_home_active);

    }

    @OnClick(R.id.f_btn_more)
    void more() {
        startActivity(new Intent(this, AccountActivity.class));
    }

    @OnClick(R.id.f_btn_search)
    void search() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.f_btn_history)
    void history() {
        replaceHistoryFragment();
    }

    @OnClick(R.id.f_btn_help)
    void help() {
        replaceHelpFragment();
    }

    @OnClick(R.id.place_order)
    void placeOrder() {
        startActivity(new Intent(this, QrCodeActivity.class));
    }

    @OnClick(R.id.delivery_order)
    void deliveryOrder() {
        startActivity(new Intent(this, LocationActivity.class));
    }

    @OnClick(R.id.book_table)
    void bookingTable() {
        startActivity(new Intent(this, BookingActivity.class));
    }

    @Override
    protected void setUp() {
        mHomePagerAdapter.setCount(3);
        mViewPager.setAdapter(mHomePagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
        mTabs.setViewPager(mViewPager);
    }

    @Override
    public void initHelpAdapter() {
        mViewPager.setAdapter(mHelpPagerAdapter);
        mHomePagerAdapter.notifyDataSetChanged();
        mTabs.notifyDataSetChanged();
    }

    @Override
    public void initHistoryAdapter() {
        mHistoryPagerAdapter.setCount(2);
        mViewPager.setAdapter(mHistoryPagerAdapter);
        mHomePagerAdapter.notifyDataSetChanged();
        mTabs.notifyDataSetChanged();
    }

    public void setCurentItemHomePager(int paramInt) {
        mViewPager.setCurrentItem(paramInt);
    }

    private void setNonActive() {
        this.btnHome.setImageResource(R.drawable.ic_home);
        this.btnHistory.setImageResource(R.drawable.ic_clock);
        this.btnHelp.setImageResource(R.drawable.ic_live_help);
    }

    @Override
    public void replaceHistoryFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_pop_left, R.anim.anim_push_left);
        transaction.replace(R.id.fragments, HistoryCompleteFragment.newInstance());
        transaction.commit();
        initHelpAdapter();
        //setCurentItemHomePager(0);
        setNonActive();
        btnHistory.setImageResource(R.drawable.ic_clock_active);
    }

    @Override
    public void replaceHelpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_pop_left, R.anim.anim_push_left);
        transaction.replace(R.id.fragments, HelpFragment.newInstance());
        transaction.commit();
        initHelpAdapter();
        //setCurentItemHomePager(0);
        setNonActive();
        btnHelp.setImageResource(R.drawable.ic_live_help_active);
    }
}
