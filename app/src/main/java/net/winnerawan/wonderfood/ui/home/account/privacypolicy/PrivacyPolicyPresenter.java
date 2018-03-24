package net.winnerawan.wonderfood.ui.home.account.privacypolicy;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class PrivacyPolicyPresenter<V extends PrivacyPolicyView> extends BasePresenter<V> implements PrivacyPolicyMvpPresenter<V> {

    @Inject
    public PrivacyPolicyPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
