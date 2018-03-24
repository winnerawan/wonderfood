package net.winnerawan.wonderfood.ui.home.menu.explore;

import net.winnerawan.wonderfood.ui.base.MvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface ExploreMvpPresenter<V extends ExploreView> extends MvpPresenter<V> {

    void onViewPrepared();

    void getPromotions();
}
