package net.winnerawan.wonderfood.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.winnerawan.wonderfood.di.ApplicationContext;
import net.winnerawan.wonderfood.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN = "PREF_KEY_USER_LOGGED_IN";
    private static final String PREF_KEY_USER_TABLE = "PREF_KEY_USER_TABLE";
    private static final String PREF_KEY_ORDER_ID = "PREF_KEY_ORDER_ID";
    private static final String PREF_KEY_MENU_EMPTY = "PREF_KEY_MENU_EMPTY";
    private static final String PREF_KEY_ADDRESS = "PREF_KEY_ADDRESS";



    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setUserLoggedIn(boolean loggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN, loggedIn).apply();
    }

    @Override
    public boolean isUserLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }

    @Override
    public void setOrderID(int orderID) {
        mPrefs.edit().putInt(PREF_KEY_ORDER_ID, orderID).apply();
    }

    @Override
    public int getOrderID() {
        return mPrefs.getInt(PREF_KEY_ORDER_ID, 0);
    }

    @Override
    public void setTable(int noTable) {
        mPrefs.edit().putInt(PREF_KEY_USER_TABLE, noTable).apply();
    }

    @Override
    public int getTable() {
        return mPrefs.getInt(PREF_KEY_USER_TABLE, 0);
    }

    @Override
    public void setMenuNotEmpty(boolean isEmpty) {
        mPrefs.edit().putBoolean(PREF_KEY_MENU_EMPTY, isEmpty).apply();
    }

    @Override
    public boolean isMenuNotEmpty() {
        return mPrefs.getBoolean(PREF_KEY_MENU_EMPTY, false);
    }

    @Override
    public void setAddress(String address) {
        mPrefs.edit().putString(PREF_KEY_ADDRESS, address).apply();
    }

    @Override
    public String getAddress() {
        return mPrefs.getString(PREF_KEY_ADDRESS, null);
    }
}
