package net.winnerawan.wonderfood.di.module;

import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import net.winnerawan.wonderfood.data.AppDataManager;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.data.db.AppDbHelper;
import net.winnerawan.wonderfood.data.db.DbHelper;
import net.winnerawan.wonderfood.data.network.ApiHeader;
import net.winnerawan.wonderfood.data.network.ApiHelper;
import net.winnerawan.wonderfood.data.network.AppApiHelper;
import net.winnerawan.wonderfood.data.prefs.AppPreferencesHelper;
import net.winnerawan.wonderfood.data.prefs.PreferencesHelper;
import net.winnerawan.wonderfood.di.ApiInfo;
import net.winnerawan.wonderfood.di.ApplicationContext;
import net.winnerawan.wonderfood.di.DatabaseInfo;
import net.winnerawan.wonderfood.di.PreferenceInfo;
import net.winnerawan.wonderfood.utils.AppConstants;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader provideApiHeader(DbHelper dbHelper) {
        if (dbHelper.getUser().getApi_key()==null || dbHelper.getUser().getApi_key().isEmpty()) {
            return new ApiHeader("");
        }
        return new ApiHeader(dbHelper.getUser().getApi_key());
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



}
