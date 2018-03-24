package net.winnerawan.wonderfood.ui.home.menu.explore;

import net.winnerawan.wonderfood.data.db.model.Menu;
import net.winnerawan.wonderfood.data.db.model.Promotion;
import net.winnerawan.wonderfood.data.network.model.MenuRequest;
import net.winnerawan.wonderfood.ui.base.MvpView;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface ExploreView extends MvpView {

    void exploreMenu(List<Menu> menus);
    void showPromo(RemoteBanner banner);
}
