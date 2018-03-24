package net.winnerawan.wonderfood.data.network;


import net.winnerawan.wonderfood.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String ENDPOINT_AUTH = BuildConfig.BASE_URL
            +"/login";
    public static final String ENDPOINT_SIGNUP = BuildConfig.BASE_URL
            +"/register";
    public static final String ENDPOINT_PROMOTIONS = BuildConfig.BASE_URL
            +"/promo";
    public static final String ENDPOINT_EXPLORE = BuildConfig.BASE_URL
            +"/menus";
    public static final String ENDPOINT_CATEGORIES = BuildConfig.BASE_URL
            +"/categories";
    public static final String ENDPOINT_FOODS = BuildConfig.BASE_URL
            +"/foods";
    public static final String ENDPOINT_BEVERAGES = BuildConfig.BASE_URL
            +"/beverages";
    public static final String ENDPOINT_INIT_ORDER = BuildConfig.BASE_URL
            +"/initOrder";
    public static final String ENDPOINT_STORE_PLACE_ORDER = BuildConfig.BASE_URL
            +"/storePlaceOrder";
    public static final String ENDPOINT_STORE_DELIVERY_ORDER = BuildConfig.BASE_URL
            +"/storeDeliveryOrder";
    public static final String ENDPOINT_UPDATE_PLACE_ORDER = BuildConfig.BASE_URL
            +"/order/";
    public static final String ENDPOINT_COMPLETE_HISTORY = BuildConfig.BASE_URL
            +"/completeOrder/";
    public static final String ENDPOINT_PLACE_CART = BuildConfig.BASE_URL
            +"/cart/place/";
    public static final String ENDPOINT_DELIVERY_CART = BuildConfig.BASE_URL
            +"/cart/delivery/";
}
