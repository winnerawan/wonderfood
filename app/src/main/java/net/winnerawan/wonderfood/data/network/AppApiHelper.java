package net.winnerawan.wonderfood.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;


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
import net.winnerawan.wonderfood.utils.AppLogger;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<SignInResponse> authentication(SignRequest.In request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_AUTH)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(SignInResponse.class);
    }

    @Override
    public Observable<BaseResponse> signup(SignRequest.Up request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGNUP)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<MenuResponse> explore(MenuRequest.Explore request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXPLORE)
                .build()
                .getObjectObservable(MenuResponse.class);
    }

    @Override
    public Observable<MenuResponse> beverages(MenuRequest.Beverages request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BEVERAGES)
                .build()
                .getObjectObservable(MenuResponse.class);    }

    @Override
    public Observable<MenuResponse> foods(MenuRequest.Foods request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_FOODS)
                .build()
                .getObjectObservable(MenuResponse.class);    }

    @Override
    public Observable<CategoryResponse> categories(MenuRequest.Categories request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CATEGORIES)
                .build()
                .getObjectObservable(CategoryResponse.class);
    }

    @Override
    public Observable<PromotionResponse> promo(MenuRequest.Promotion request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PROMOTIONS)
                .build()
                .getObjectObservable(PromotionResponse.class);
    }

    @Override
    public Observable<OrderResponse.Init> initOrder(OrderRequest.Init request) {
        AppLogger.e("API KEY CHECK.."+String.valueOf(getApiHeader().getAuthorization()));
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_INIT_ORDER)
                .addHeaders("Authorization", getApiHeader().getAuthorization())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(OrderResponse.Init.class);
    }

    @Override
    public Observable<OrderResponse.Store> storePlaceOrder(OrderRequest.Place request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_STORE_PLACE_ORDER)
                .addHeaders("Authorization", getApiHeader().getAuthorization())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(OrderResponse.Store.class);
    }

    @Override
    public Observable<OrderResponse.Store> storeDeliveryOrder(OrderRequest.Delivery request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_STORE_DELIVERY_ORDER)
                .addHeaders("Authorization", getApiHeader().getAuthorization())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(OrderResponse.Store.class);
    }

    @Override
    public Observable<BaseResponse> updatePlaceOrder(OrderRequest.Place.Update request) {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_UPDATE_PLACE_ORDER+request.getOrder_id())
                .addHeaders("Authorization", getApiHeader().getAuthorization())
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<HistoryResponse> completeHistory(HistoryRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_COMPLETE_HISTORY+request.getCustomer_id())
                .addHeaders("Authorization", getApiHeader().getAuthorization())
                .build()
                .getObjectObservable(HistoryResponse.class);
    }

}
