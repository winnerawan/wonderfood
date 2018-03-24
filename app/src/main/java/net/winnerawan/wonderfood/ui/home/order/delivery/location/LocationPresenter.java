package net.winnerawan.wonderfood.ui.home.order.delivery.location;

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
public class LocationPresenter<V extends LocationView> extends BasePresenter<V> implements LocationMvpPresenter<V> {

    @Inject
    public LocationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void initOrder(String address) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .initOrder(new OrderRequest.Init(getDataManager().getUser().getId()))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(init -> {
                    if (!init.getError() && init.getOrderId() != null) {
                        AppLogger.e("INIT"+init.getOrderId());
                        getMvpView().hideLoading();
                        getDataManager().setOrderID(init.getOrderId());
                        getDataManager().setAddress(address);
                        getMvpView().gotoSelectMenu();

                    }
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
