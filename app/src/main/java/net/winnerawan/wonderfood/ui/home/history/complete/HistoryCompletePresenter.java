package net.winnerawan.wonderfood.ui.home.history.complete;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.HistoryRequest;
import net.winnerawan.wonderfood.data.network.model.HistoryResponse;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
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
public class HistoryCompletePresenter<V extends HistoryCompleteView> extends BasePresenter<V> implements HistoryCompleteMvpPresenter<V> {

    @Inject
    public HistoryCompletePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadCompleteHistory() {

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().completeHistory(new HistoryRequest(getDataManager().getUser().getId()))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(historyResponse -> {
                    if (historyResponse!=null && historyResponse.getHistory()!=null) {
                        getMvpView().showHistory(historyResponse.getHistory());
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
