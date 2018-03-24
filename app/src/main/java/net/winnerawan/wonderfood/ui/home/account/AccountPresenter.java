package net.winnerawan.wonderfood.ui.home.account;

import net.winnerawan.wonderfood.data.DataManager;
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

public class AccountPresenter<V extends AccountView> extends BasePresenter<V> implements AccountMvpPresenter<V> {

    @Inject
    public AccountPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void signOut() {
        getMvpView().showLoading();
        getDataManager().setUserLoggedIn(false);
        getDataManager().removeUser();
        getMvpView().hideLoading();
        getMvpView().gotoSignIn();
    }
}
