package net.winnerawan.wonderfood.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

import net.winnerawan.wonderfood.di.ActivityContext;
import net.winnerawan.wonderfood.di.PerActivity;
import net.winnerawan.wonderfood.ui.home.HelpPagerAdapter;
import net.winnerawan.wonderfood.ui.home.account.about.AboutMvpPresenter;
import net.winnerawan.wonderfood.ui.home.account.about.AboutPresenter;
import net.winnerawan.wonderfood.ui.home.account.about.AboutView;
import net.winnerawan.wonderfood.ui.home.account.privacypolicy.PrivacyPolicyMvpPresenter;
import net.winnerawan.wonderfood.ui.home.account.privacypolicy.PrivacyPolicyPresenter;
import net.winnerawan.wonderfood.ui.home.account.privacypolicy.PrivacyPolicyView;
import net.winnerawan.wonderfood.ui.home.account.term.TermCondsMvpPresenter;
import net.winnerawan.wonderfood.ui.home.account.term.TermCondsPresenter;
import net.winnerawan.wonderfood.ui.home.account.term.TermCondsView;
import net.winnerawan.wonderfood.ui.home.history.HistoryAdapter;
import net.winnerawan.wonderfood.ui.home.history.HistoryPagerAdapter;
import net.winnerawan.wonderfood.ui.home.HomeMvpPresenter;
import net.winnerawan.wonderfood.ui.home.HomePresenter;
import net.winnerawan.wonderfood.ui.home.HomeView;
import net.winnerawan.wonderfood.ui.home.account.AccountMvpPresenter;
import net.winnerawan.wonderfood.ui.home.account.AccountPresenter;
import net.winnerawan.wonderfood.ui.home.account.AccountView;
import net.winnerawan.wonderfood.ui.home.booking.BookingMvpPresenter;
import net.winnerawan.wonderfood.ui.home.booking.BookingPresenter;
import net.winnerawan.wonderfood.ui.home.booking.BookingView;
import net.winnerawan.wonderfood.ui.home.help.HelpMvpPresenter;
import net.winnerawan.wonderfood.ui.home.help.HelpPresenter;
import net.winnerawan.wonderfood.ui.home.help.HelpView;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompleteMvpPresenter;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompleteView;
import net.winnerawan.wonderfood.ui.home.history.complete.HistoryCompletePresenter;
import net.winnerawan.wonderfood.ui.home.history.progress.HistoryProgressMvpPresenter;
import net.winnerawan.wonderfood.ui.home.history.progress.HistoryProgressPresenter;
import net.winnerawan.wonderfood.ui.home.history.progress.HistoryProgressView;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageAdapter;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeveragePresenter;
import net.winnerawan.wonderfood.ui.home.menu.beverage.BeverageView;
import net.winnerawan.wonderfood.ui.home.menu.detail.DetailMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.detail.DetailPresenter;
import net.winnerawan.wonderfood.ui.home.menu.detail.DetailView;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExploreAdapter;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExploreMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExplorePresenter;
import net.winnerawan.wonderfood.ui.home.menu.explore.ExploreView;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodAdapter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodMvpPresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodPresenter;
import net.winnerawan.wonderfood.ui.home.menu.food.FoodView;
import net.winnerawan.wonderfood.ui.home.HomePagerAdapter;
import net.winnerawan.wonderfood.ui.home.order.delivery.DSelectPagerAdapter;
import net.winnerawan.wonderfood.ui.home.order.delivery.food.DSelectFoodMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.food.DSelectFoodPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.food.DSelectFoodView;
import net.winnerawan.wonderfood.ui.home.order.place.PSelectPagerAdapter;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryView;
import net.winnerawan.wonderfood.ui.home.order.delivery.location.LocationMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.location.LocationPresenter;
import net.winnerawan.wonderfood.ui.home.order.delivery.location.LocationView;
import net.winnerawan.wonderfood.ui.home.order.place.PlaceMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.place.PlacePresenter;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeverageAdapter;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeverageMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeveragePresenter;
import net.winnerawan.wonderfood.ui.home.order.place.beverage.PSelectBeverageView;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodAdapter;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodPresenter;
import net.winnerawan.wonderfood.ui.home.order.place.food.PSelectFoodView;
import net.winnerawan.wonderfood.ui.home.order.place.PlaceView;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodeMvpPresenter;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodePresenter;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodeView;
import net.winnerawan.wonderfood.ui.home.search.SearchAdapter;
import net.winnerawan.wonderfood.ui.home.search.SearchMvpPresenter;
import net.winnerawan.wonderfood.ui.home.search.SearchPresenter;
import net.winnerawan.wonderfood.ui.home.search.SearchView;
import net.winnerawan.wonderfood.ui.sign.in.SignInMvpPresenter;
import net.winnerawan.wonderfood.ui.sign.in.SignInPresenter;
import net.winnerawan.wonderfood.ui.sign.in.SignInView;
import net.winnerawan.wonderfood.ui.sign.up.SignUpMvpPresenter;
import net.winnerawan.wonderfood.ui.sign.up.SignUpPresenter;
import net.winnerawan.wonderfood.ui.sign.up.SignUpView;
import net.winnerawan.wonderfood.ui.splash.SplashMvpPresenter;
import net.winnerawan.wonderfood.ui.splash.SplashPresenter;
import net.winnerawan.wonderfood.ui.splash.SplashView;
import net.winnerawan.wonderfood.utils.rx.AppSchedulerProvider;
import net.winnerawan.wonderfood.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashView> provideSplashPresenter(
            SplashPresenter<SplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SignInMvpPresenter<SignInView> provideSignInPresenter(
            SignInPresenter<SignInView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SignUpMvpPresenter<SignUpView> provideSignUpPresenter(
            SignUpPresenter<SignUpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HomeMvpPresenter<HomeView> provideHomePresenter(
            HomePresenter<HomeView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AccountMvpPresenter<AccountView> provideAccountPresenter(
            AccountPresenter<AccountView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchView> provideSearchPresenter(
            SearchPresenter<SearchView> presenter) {
        return presenter;
    }

    @Provides
    SearchAdapter provideSearchAdapter() {
        return new SearchAdapter(new ArrayList<>());
    }

    @Provides
    HomePagerAdapter provideHomePagerAdapter(AppCompatActivity activity) {
        return new HomePagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    BeverageMvpPresenter<BeverageView> provideBeveragePresenter(
            BeveragePresenter<BeverageView> presenter) {
        return presenter;
    }

    @Provides
    ExploreMvpPresenter<ExploreView> provideExplorePresenter(
            ExplorePresenter<ExploreView> presenter) {
        return presenter;
    }

    @Provides
    FoodMvpPresenter<FoodView> provideFoodPresenter(
            FoodPresenter<FoodView> presenter) {
        return presenter;
    }

    @Provides
    ExploreAdapter provideExploreAdapter() {
        return new ExploreAdapter(new ArrayList<>());
    }

    @Provides
    BeverageAdapter provideBeverageAdapter() {
        return new BeverageAdapter(new ArrayList<>());
    }

    @Provides
    FoodAdapter provideFoodAdapter() {
        return new FoodAdapter(new ArrayList<>());
    }

    @Provides
    HelpPagerAdapter provideHelpPagerAdapter(AppCompatActivity activity) {
        return new HelpPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    HelpMvpPresenter<HelpView> provideHelpPresenter(
            HelpPresenter<HelpView> presenter) {
        return presenter;
    }

    @Provides
    HistoryPagerAdapter provideHistoryPagerAdapter(AppCompatActivity activity) {
        return new HistoryPagerAdapter(activity.getSupportFragmentManager(), activity);
    }

    @Provides
    HistoryProgressMvpPresenter<HistoryProgressView> provideHistoryProgressPresenter(
            HistoryProgressPresenter<HistoryProgressView> presenter) {
        return presenter;
    }

    @Provides
    HistoryCompleteMvpPresenter<HistoryCompleteView> provideHistoryCompletePresenter(
            HistoryCompletePresenter<HistoryCompleteView> presenter) {
        return presenter;
    }

    @Provides
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    DeliveryMvpPresenter<DeliveryView> provideDeliveryPresenter(
            DeliveryPresenter<DeliveryView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LocationMvpPresenter<LocationView> provideLocationPresenter(
            LocationPresenter<LocationView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    QrCodeMvpPresenter<QrCodeView> provideQrCodePresenter(
            QrCodePresenter<QrCodeView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PlaceMvpPresenter<PlaceView> providePlacePresenter(
            PlacePresenter<PlaceView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BookingMvpPresenter<BookingView> provideBookingPresenter(
            BookingPresenter<BookingView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailView> provideDetailPresenter(
            DetailPresenter<DetailView> presenter) {
        return presenter;
    }

    @Provides
    PSelectPagerAdapter providePSelectPagerAdapter(AppCompatActivity activity) {
        return new PSelectPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    DSelectPagerAdapter provideDSelectPagerAdapter(AppCompatActivity activity) {
        return new DSelectPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    DSelectFoodMvpPresenter<DSelectFoodView> provideDSelectFoodPresenter(
            DSelectFoodPresenter<DSelectFoodView> presenter) {
        return presenter;
    }

    @Provides
    PSelectFoodMvpPresenter<PSelectFoodView> providePSelectFoodPresenter(
            PSelectFoodPresenter<PSelectFoodView> presenter) {
        return presenter;
    }

    @Provides
    PSelectFoodAdapter providePSelectFoodAdapter() {
        return new PSelectFoodAdapter(new ArrayList<>());
    }

    @Provides
    PSelectBeverageMvpPresenter<PSelectBeverageView> providePSelectBeveragePresenter(
            PSelectBeveragePresenter<PSelectBeverageView> presenter) {
        return presenter;
    }

    @Provides
    PSelectBeverageAdapter providePSelectBeverageAdapter() {
        return new PSelectBeverageAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    PrivacyPolicyMvpPresenter<PrivacyPolicyView> providePrivacyPolicyPresenter(
            PrivacyPolicyPresenter<PrivacyPolicyView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    TermCondsMvpPresenter<TermCondsView> provideTermCondsPresenter(
            TermCondsPresenter<TermCondsView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AboutMvpPresenter<AboutView> provideAboutPresenter(
            AboutPresenter<AboutView> presenter) {
        return presenter;
    }
}
