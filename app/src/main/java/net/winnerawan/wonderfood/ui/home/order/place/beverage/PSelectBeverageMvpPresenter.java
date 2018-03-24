package net.winnerawan.wonderfood.ui.home.order.place.beverage;

import net.winnerawan.wonderfood.ui.base.MvpPresenter;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface PSelectBeverageMvpPresenter<V extends PSelectBeverageView> extends MvpPresenter<V> {

    void onViewPrepared();
    void storeOrder(int menu_id, int qty, double price);
}
