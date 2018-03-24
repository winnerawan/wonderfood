package net.winnerawan.wonderfood.data.db;

import net.winnerawan.wonderfood.data.db.model.DaoMaster;
import net.winnerawan.wonderfood.data.db.model.DaoSession;
import net.winnerawan.wonderfood.data.db.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public void addUser(User user) {
        if (user!=null) {
            removeUser();
        }
        mDaoSession.getUserDao().insert(user);
    }

    @Override
    public User getUser() {
        if (mDaoSession.getUserDao()==null || mDaoSession.getUserDao().loadAll().isEmpty()) {
            return new User();
        }
        return mDaoSession.getUserDao().loadAll().get(0);
    }

    @Override
    public void removeUser() {
        mDaoSession.deleteAll(User.class);
    }

    @Override
    public String getApiKey() {
        return mDaoSession.getUserDao().loadAll().get(0).getApi_key();
    }
}
