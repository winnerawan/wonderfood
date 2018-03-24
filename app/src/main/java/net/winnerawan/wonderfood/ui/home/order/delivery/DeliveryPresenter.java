package net.winnerawan.wonderfood.ui.home.order.delivery;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.OrderRequest;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.utils.AppLogger;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class DeliveryPresenter<V extends DeliveryView> extends BasePresenter<V> implements DeliveryMvpPresenter<V> {

    @Inject
    public DeliveryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void check() {
        getMvpView().showLoading();
        if (getDataManager().isMenuNotEmpty()) {
            getMvpView().showDialogOrderReceived();
        } else {
            getMvpView().shoDialogMenuEmpty();
        }
    }
}
