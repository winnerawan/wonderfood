package net.winnerawan.wonderfood.data.db;

import net.winnerawan.wonderfood.data.db.model.Order;
import net.winnerawan.wonderfood.data.db.model.User;

import io.reactivex.Observable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface DbHelper {

    void addUser(User user);
    User getUser();
    void removeUser();
    String getApiKey();
}
