package net.winnerawan.wonderfood.data.network;


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

import io.reactivex.Observable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<SignInResponse> authentication(SignRequest.In request);

    Observable<BaseResponse> signup(SignRequest.Up request);

    Observable<MenuResponse> explore(MenuRequest.Explore request);

    Observable<MenuResponse> beverages(MenuRequest.Beverages request);

    Observable<MenuResponse> foods(MenuRequest.Foods request);

    Observable<CategoryResponse> categories(MenuRequest.Categories request);

    Observable<PromotionResponse> promo(MenuRequest.Promotion request);

    Observable<OrderResponse.Init> initOrder(OrderRequest.Init request);

    Observable<OrderResponse.Store> storePlaceOrder(OrderRequest.Place request);

    Observable<OrderResponse.Store> storeDeliveryOrder(OrderRequest.Delivery request);

    Observable<BaseResponse> updatePlaceOrder(OrderRequest.Place.Update request);

    Observable<HistoryResponse> completeHistory(HistoryRequest request);

}
