package net.winnerawan.wonderfood.ui.home.menu.beverage;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.MenuRequest;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodView;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class BeveragePresenter<V extends BeverageView> extends BasePresenter<V> implements BeverageMvpPresenter<V> {

    @Inject
    public BeveragePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .beverages(new MenuRequest.Beverages())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(menuResponse -> {
                    if (menuResponse!=null && menuResponse.getMenus()!=null) {
                        getMvpView().showBeverages(menuResponse.getMenus());
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
