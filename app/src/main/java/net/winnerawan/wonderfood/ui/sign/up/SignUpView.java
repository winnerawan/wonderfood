package net.winnerawan.wonderfood.ui.sign.up;

import net.winnerawan.wonderfood.ui.base.MvpView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface SignUpView extends MvpView {


    void gotoSignInActivity();

    boolean isAgreeTermConditions();
}
