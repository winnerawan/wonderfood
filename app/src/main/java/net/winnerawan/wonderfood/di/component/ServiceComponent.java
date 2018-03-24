package net.winnerawan.wonderfood.di.component;

import dagger.Component;
import net.winnerawan.wonderfood.di.PerService;
import net.winnerawan.wonderfood.di.module.ServiceModule;
import net.winnerawan.wonderfood.service.SyncService;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
