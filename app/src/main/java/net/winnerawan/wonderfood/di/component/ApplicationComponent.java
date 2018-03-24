package net.winnerawan.wonderfood.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

import net.winnerawan.wonderfood.Wonderfood;
import net.winnerawan.wonderfood.data.DataManager;
import net.winnerawan.wonderfood.di.ApplicationContext;
import net.winnerawan.wonderfood.di.module.ApplicationModule;
import net.winnerawan.wonderfood.service.SyncService;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Wonderfood app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();}
