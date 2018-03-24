package net.winnerawan.wonderfood.di.component;

import dagger.Component;
import net.winnerawan.wonderfood.di.PerActivity;
import net.winnerawan.wonderfood.di.module.ActivityModule;
import net.winnerawan.wonderfood.ui.home.HomeActivity;
import net.winnerawan.wonderfood.ui.home.account.AccountActivity;
import net.winnerawan.wonderfood.ui.home.account.about.AboutActivity;
import net.winnerawan.wonderfood.ui.home.account.privacypolicy.PrivacyPolicyActivity;
import net.winnerawan.wonderfood.ui.home.account.term.TermCondsActivity;
import net.winnerawan.wonderfood.ui.home.booking.BookingActivity;
import net.winnerawan.wonderfood.ui.home.help.HelpFragment;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompleteFragment;
import net.winnerawan.wonderfood.ui.home.history.progress.HistoryProgressFragment;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageFragment;
import net.winnerawan.wonderfood.ui.home.menu.detail.DetailActivty;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExploreFragment;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodFragment;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryActivity;
import net.winnerawan.wonderfood.ui.home.order.delivery.food.DSelectFoodFragment;
import net.winnerawan.wonderfood.ui.home.order.delivery.location.LocationActivity;
import net.winnerawan.wonderfood.ui.home.order.place.PlaceActivity;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeverageFragment;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodFragment;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodeActivity;
import net.winnerawan.wonderfood.ui.home.search.SearchActivity;
import net.winnerawan.wonderfood.ui.sign.in.SignInActivity;
import net.winnerawan.wonderfood.ui.sign.up.SignUpActivity;
import net.winnerawan.wonderfood.ui.splash.SplashActivity;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(SignInActivity activity);
    void inject(SignUpActivity activity);
    void inject(HomeActivity activity);
    void inject(AccountActivity activity);
    void inject(SearchActivity activity);
    void inject(ExploreFragment fragment);
    void inject(HelpFragment fragment);
    void inject(HistoryProgressFragment fragment);
    void inject(HistoryCompleteFragment fragment);
    void inject(DeliveryActivity activity);
    void inject(LocationActivity activity);
    void inject(QrCodeActivity activity);
    void inject(PlaceActivity activity);
    void inject(BookingActivity activity);
    void inject(BeverageFragment fragment);
    void inject(FoodFragment fragment);
    void inject(DetailActivty activty);
    void inject(PSelectFoodFragment fragment);
    void inject(PSelectBeverageFragment fragment);
    void inject(PrivacyPolicyActivity activity);
    void inject(TermCondsActivity activity);
    void inject(AboutActivity activity);
    void inject(DSelectFoodFragment fragment);
}
