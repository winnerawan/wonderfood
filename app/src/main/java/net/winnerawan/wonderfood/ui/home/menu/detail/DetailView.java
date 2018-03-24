package net.winnerawan.wonderfood.ui.home.menu.detail;

import android.os.Bundle;

import net.winnerawan.wonderfood.ui.base.MvpView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface DetailView extends MvpView {

    void initFloatingActionButton();
    void gotoScanQR();
    void gotoDelivery();
}
