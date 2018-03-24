package net.winnerawan.wonderfood.ui.home.order.place.qr;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.db.model.User;
import net.winnerawan.wonderfood.data.network.model.OrderRequest;
import net.winnerawan.wonderfood.data.network.model.OrderResponse;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.utils.AppLogger;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class QrCodePresenter<V extends QrCodeView> extends BasePresenter<V> implements QrCodeMvpPresenter<V> {

    @Inject
    public QrCodePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void cameraPermissionGranted() {
        getMvpView().showMessage(R.string.message_camera_permission_granted);
    }

    @Override
    public void deniedCameraPermission() {
        getMvpView().showMessage(R.string.message_camera_permission_denied);
    }

    @Override
    public void initOrder(int noTable) {
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
                        getDataManager().setTable(noTable);
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
