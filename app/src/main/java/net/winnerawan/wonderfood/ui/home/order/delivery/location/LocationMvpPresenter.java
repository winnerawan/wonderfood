package net.winnerawan.wonderfood.ui.home.order.delivery.location;

import net.winnerawan.wonderfood.ui.base.MvpPresenter;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface LocationMvpPresenter<V extends LocationView> extends MvpPresenter<V> {

    void initOrder(String address);
}
