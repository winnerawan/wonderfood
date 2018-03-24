package net.winnerawan.wonderfood.ui.home.order.delivery;

import net.winnerawan.wonderfood.ui.base.MvpView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface DeliveryView extends MvpView {

    void shoDialogMenuEmpty();

    void showDialogOrderReceived();

    void gotoLocationActivity();
}
