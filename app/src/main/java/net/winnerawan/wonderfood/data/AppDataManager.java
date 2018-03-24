package net.winnerawan.wonderfood.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.winnerawan.wonderfood.data.db.DbHelper;
import net.winnerawan.wonderfood.data.db.model.User;
import net.winnerawan.wonderfood.data.network.ApiHeader;
import net.winnerawan.wonderfood.data.network.ApiHelper;
import net.winnerawan.wonderfood.data.network.model.BaseResponse;
import net.winnerawan.wonderfood.data.network.model.CategoryResponse;
import net.winnerawan.wonderfood.data.network.model.HistoryRequest;
import net.winnerawan.wonderfood.data.network.model.HistoryResponse;
import net.winnerawan.wonderfood.data.network.model.MenuRequest;
import net.winnerawan.wonderfood.data.network.model.MenuResponse;
import net.winnerawan.wonderfood.data.network.model.OrderRequest;
import net.winnerawan.wonderfood.data.network.model.OrderResponse;
import net.winnerawan.wonderfood.data.network.model.PromotionResponse;
import net.winnerawan.wonderfood.data.network.model.SignInResponse;
import net.winnerawan.wonderfood.data.network.model.SignRequest;
import net.winnerawan.wonderfood.data.prefs.PreferencesHelper;
import net.winnerawan.wonderfood.di.ApplicationContext;

import io.reactivex.Observable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getApiKey() {
        return mDbHelper.getApiKey();
    }

    @Override
    public Observable<SignInResponse> authentication(SignRequest.In request) {
        return mApiHelper.authentication(request);
    }

    @Override
    public Observable<BaseResponse> signup(SignRequest.Up request) {
        return mApiHelper.signup(request);
    }

    @Override
    public void setUserLoggedIn(boolean loggedIn) {
        mPreferencesHelper.setUserLoggedIn(loggedIn);
    }

    @Override
    public boolean isUserLoggedIn() {
        return mPreferencesHelper.isUserLoggedIn();
    }

    @Override
    public void addUser(User user) {
        mDbHelper.addUser(user);
    }

    @Override
    public User getUser() {
        return mDbHelper.getUser();
    }

    @Override
    public void removeUser() {
        mDbHelper.removeUser();
    }

    @Override
    public Observable<MenuResponse> explore(MenuRequest.Explore request) {
        return mApiHelper.explore(request);
    }

    @Override
    public Observable<MenuResponse> beverages(MenuRequest.Beverages request) {
        return mApiHelper.beverages(request);
    }

    @Override
    public Observable<MenuResponse> foods(MenuRequest.Foods request) {
        return mApiHelper.foods(request);
    }

    @Override
    public Observable<CategoryResponse> categories(MenuRequest.Categories request) {
        return mApiHelper.categories(request);
    }

    @Override
    public Observable<PromotionResponse> promo(MenuRequest.Promotion request) {
        return mApiHelper.promo(request);
    }

    @Override
    public void setOrderID(int orderID) {
        mPreferencesHelper.setOrderID(orderID);
    }

    @Override
    public int getOrderID() {
        return mPreferencesHelper.getOrderID();
    }

    @Override
    public void setTable(int noTable) {
        mPreferencesHelper.setTable(noTable);
    }

    @Override
    public int getTable() {
        return mPreferencesHelper.getTable();
    }

    @Override
    public void setMenuNotEmpty(boolean isEmpty) {
        mPreferencesHelper.setMenuNotEmpty(isEmpty);
    }

    @Override
    public boolean isMenuNotEmpty() {
        return mPreferencesHelper.isMenuNotEmpty();
    }

    @Override
    public Observable<OrderResponse.Init> initOrder(OrderRequest.Init request) {
        return mApiHelper.initOrder(request);
    }

    @Override
    public Observable<OrderResponse.Store> storePlaceOrder(OrderRequest.Place request) {
        return mApiHelper.storePlaceOrder(request);
    }

    @Override
    public Observable<OrderResponse.Store> storeDeliveryOrder(OrderRequest.Delivery request) {
        return mApiHelper.storeDeliveryOrder(request);
    }

    @Override
    public Observable<BaseResponse> updatePlaceOrder(OrderRequest.Place.Update request) {
        return mApiHelper.updatePlaceOrder(request);
    }

    @Override
    public Observable<HistoryResponse> completeHistory(HistoryRequest request) {
        return mApiHelper.completeHistory(request);
    }

    @Override
    public void setAddress(String address) {
        mPreferencesHelper.setAddress(address);
    }

    @Override
    public String getAddress() {
        return mPreferencesHelper.getAddress();
    }
}
