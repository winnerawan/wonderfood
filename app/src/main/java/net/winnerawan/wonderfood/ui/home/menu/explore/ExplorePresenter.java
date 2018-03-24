package net.winnerawan.wonderfood.ui.home.menu.explore;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.MenuRequest;
import net.winnerawan.wonderfood.data.network.model.MenuResponse;
import net.winnerawan.wonderfood.data.network.model.PromotionResponse;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodView;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ss.com.bannerslider.banners.RemoteBanner;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class ExplorePresenter<V extends ExploreView> extends BasePresenter<V> implements ExploreMvpPresenter<V> {

    @Inject
    public ExplorePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getPromotions() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .promo(new MenuRequest.Promotion())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(promotionResponse -> {
                    if (promotionResponse!=null && promotionResponse.getPromotions()!=null) {
                        for (int i=0; i<promotionResponse.getPromotions().size(); i++) {
                            getMvpView().showPromo(new RemoteBanner(promotionResponse.getPromotions().get(i).getImage()));
                        }
                    }
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
    public void onViewPrepared() {

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .explore(new MenuRequest.Explore())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(menuResponse -> {
                    if (menuResponse!=null && menuResponse.getMenus()!=null) {
                        getMvpView().exploreMenu(menuResponse.getMenus());
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
