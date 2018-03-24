package net.winnerawan.wonderfood.ui.home;

import net.winnerawan.wonderfood.ui.base.MvpView;

import java.util.List;

import ss.com.bannerslider.banners.Banner;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface HomeView extends MvpView {

    void initHelpAdapter();

    void initHistoryAdapter();

    void replaceHistoryFragment();

    void replaceHelpFragment();

}
