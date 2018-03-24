package net.winnerawan.wonderfood.data.prefs;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface PreferencesHelper {

    void setUserLoggedIn(boolean loggedIn);
    boolean isUserLoggedIn();
    void setOrderID(int orderID);
    int getOrderID();
    void setTable(int noTable);
    int getTable();
    void setMenuNotEmpty(boolean isEmpty);
    boolean isMenuNotEmpty();
    void setAddress(String address);
    String getAddress();
}
