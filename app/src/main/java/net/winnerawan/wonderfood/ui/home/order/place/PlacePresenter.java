package net.winnerawan.wonderfood.ui.home.order.place;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.OrderRequest;
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
public class PlacePresenter<V extends PlaceView> extends BasePresenter<V> implements PlaceMvpPresenter<V> {

    @Inject
    public PlacePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void updateOrder() {
        getMvpView().showLoading();
        if (getDataManager().isMenuNotEmpty()) {

            getCompositeDisposable().add(getDataManager().updatePlaceOrder(new OrderRequest.Place.Update(getDataManager().getOrderID()))
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeOn(getSchedulerProvider().io())
                    .subscribe(baseResponse -> {
                        if (!baseResponse.getError()) {
                            getMvpView().showDialogOrderReceived();
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
        } else {
            getMvpView().hideLoading();
            getMvpView().shoDialogMenuEmpty();
        }
    }
}
