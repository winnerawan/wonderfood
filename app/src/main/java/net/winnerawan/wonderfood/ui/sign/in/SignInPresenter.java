package net.winnerawan.wonderfood.ui.sign.in;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.db.model.User;
import net.winnerawan.wonderfood.data.network.model.SignInResponse;
import net.winnerawan.wonderfood.data.network.model.SignRequest;
import net.winnerawan.wonderfood.ui.base.BasePresenter;
import net.winnerawan.wonderfood.utils.CommonUtils;
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
public class SignInPresenter<V extends SignInView> extends BasePresenter<V> implements SignInMvpPresenter<V> {

    @Inject
    public SignInPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        if (getDataManager().isUserLoggedIn()) {
            getMvpView().gotoHomeActivity();
        }
    }

    @Override
    public void authentication(String email, String password) {
        if (email.isEmpty()) {
            getMvpView().onError(R.string.message_empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.message_invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .authentication(new SignRequest.In(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(signInResponse -> {
                    boolean error = signInResponse.getError();
                    if (!error) {
                        User user = new User(signInResponse.getId(), signInResponse.getName(), signInResponse.getEmail(), signInResponse.getApiKey(),
                                            signInResponse.getCreatedAt());
                        getMvpView().gotoHomeActivity();
                        getDataManager().setUserLoggedIn(true);
                        getDataManager().getApiHeader().setAuthorization(user.getApi_key());
                        getDataManager().addUser(user);
                    } else {
                        getMvpView().onError(R.string.message_wrong_credential);
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
                    // handle error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
