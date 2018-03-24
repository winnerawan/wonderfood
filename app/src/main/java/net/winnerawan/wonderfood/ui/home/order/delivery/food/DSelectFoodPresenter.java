package net.winnerawan.wonderfood.ui.home.order.delivery.food;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.MenuRequest;
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
public class DSelectFoodPresenter<V extends DSelectFoodView> extends BasePresenter<V> implements DSelectFoodMvpPresenter<V> {

    @Inject
    public DSelectFoodPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewPrepared() {

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .foods(new MenuRequest.Foods())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(menuResponse -> {
                    if (menuResponse!=null && menuResponse.getMenus()!=null) {
                        getMvpView().showFoods(menuResponse.getMenus());
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

    @Override
    public void storeOrder(int menu_id, int qty, double price) {
            getMvpView().showLoading();

            getCompositeDisposable().add(getDataManager().storeDeliveryOrder(new OrderRequest.Delivery(getDataManager()
                    .getOrderID(), menu_id, qty, getDataManager().getAddress(), "", "", price))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(store -> {
                        if (store!=null && !store.getError()) {
                            getMvpView().showMessage(R.string.added_to_cart);
                            getDataManager().setMenuNotEmpty(true);
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
