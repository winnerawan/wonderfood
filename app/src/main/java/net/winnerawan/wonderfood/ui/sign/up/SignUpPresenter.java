package net.winnerawan.wonderfood.ui.sign.up;

import com.androidnetworking.error.ANError;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.network.model.BaseResponse;
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
public class SignUpPresenter<V extends SignUpView> extends BasePresenter<V> implements SignUpMvpPresenter<V> {

    @Inject
    public SignUpPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void signup(String name, String email, String password) {
        if (name.isEmpty()) {
            getMvpView().onError(R.string.message_empty_name);
            return;
        }
        if (email.isEmpty()) {
            getMvpView().onError(R.string.message_empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.message_invalid_email);
            return;
        }
        if (password.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
            return;
        }
        if (!getMvpView().isAgreeTermConditions()) {
            getMvpView().onError(R.string.message_agree_termandconditions);
            return;
        }

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .signup(new SignRequest.Up(name, email, password))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(baseResponse -> {
                    if (baseResponse!=null && !baseResponse.getError()) {
                        getMvpView().showMessage(R.string.message_success_signup);
                        getMvpView().gotoSignInActivity();
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
