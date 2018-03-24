package net.winnerawan.wonderfood.ui.home.order.delivery.location;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.DirectionDrawHelper;
import net.winnerawan.wonderfood.ui.helper.FareView;
import net.winnerawan.wonderfood.ui.helper.Jarak;
import net.winnerawan.wonderfood.ui.helper.Utils;
import net.winnerawan.wonderfood.ui.helper.VMargin;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryActivity;
import net.winnerawan.wonderfood.utils.AppConstants;
import net.winnerawan.wonderfood.utils.AppLogger;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class LocationActivity extends BaseActivity implements LocationView,
        PlaceSelectionListener, OnMapReadyCallback, LocationListener, DirectionDrawHelper.OnNavigateReadyListener {

    @Inject
    LocationMvpPresenter<LocationView> mPresenter;

    @BindView(R.id.address)
    AutoCompleteTextView txtAddress;
    @BindView(R.id.charges_view)
    FareView mChargeView;
    @BindView(R.id.tariffviewLinearLayout2)
    View mFareView;
    @BindView(R.id.searcharea)
    View mSearchArea;

    private GoogleMap gMaps;
    private Geocoder mGeocoder;
    private DirectionDrawHelper mDirection;
    private VMargin vMargin;
    private static final LatLngBounds mOrigin = new LatLngBounds(new LatLng(-7.6388301, 111.535121),
            new LatLng(-7.6388301, 111.535121));
    private static final int REQUEST_SELECT_PLACE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_service);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_up, R.anim.anim_push_up);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.order)
    void order() {
        mPresenter.initOrder(txtAddress.getText().toString());
    }

    @OnClick(R.id.tariffviewMyLoc)
    void getCurrentLocation() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @OnClick(R.id.address)
    void setDestination() {
        try {
            Intent i = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setBoundsBias(mOrigin).build(LocationActivity.this);
            startActivityForResult(i, REQUEST_SELECT_PLACE);
        } catch (GooglePlayServicesRepairableException |
                GooglePlayServicesNotAvailableException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void setUp() {
        calculateMargin();
        ViewPropertyAnimator mSearchAreaAnimator = mSearchArea.animate();
        mChargeView.hide(true);
        PlaceAutocompleteFragment mDestinationAutoComplete = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_fragment);
        mDestinationAutoComplete.setOnPlaceSelectedListener(this);
        mDestinationAutoComplete.setHint(getString(R.string.setlocation));
        mDestinationAutoComplete.setBoundsBias(mOrigin);
        SupportMapFragment mapf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapf.getMapAsync(this);
    }

    @Override
    public void onPlaceSelected(Place place) {
        txtAddress.setTextSize(10);
        txtAddress.setText(place.getAddress());
        Utils.requestCenterCamera(this, gMaps, AppConstants.RESTAURANT_LATITUDE, place.getLatLng(), vMargin, null);
        gMaps.addMarker(new MarkerOptions().position(place.getLatLng()).title(getString(R.string.destination)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker)));
        if (DirectionDrawHelper.anim != null) {
            DirectionDrawHelper.anim.clearPolyline();
            DirectionDrawHelper.add_startMarker.remove();
            DirectionDrawHelper.add_endMarker.remove();
        }
        UIUtil.hideKeyboard(this);
        mDirection = new DirectionDrawHelper(this, gMaps, AppConstants.RESTAURANT_LATITUDE, place.getLatLng(), vMargin);
        mDirection.setOnNavigateReadyListener(this);
        mDirection.start();
    }

    @Override
    public void onNavigationReady(Polyline path, Jarak jarak) {
        double distance = jarak.distanceInKm;
        AppLogger.e("jarak = "+distance +" kilometer");
        if (distance>25.000) {
            new LovelyStandardDialog(this)
                    .setTopColorRes(R.color.red)
                    .setTitle(R.string.too_far)
                    .setMessage(R.string.too_far_message)
                    .setPositiveButton(android.R.string.ok, view -> {
                        finishAction();
                    })
                    .show();
            return;
        } else {
            mChargeView.setTarifByJarak(distance);
            mChargeView.setJarak(getString(R.string.distance)+" "+String.valueOf(distance) + getString(R.string.km));
            mChargeView.show();
        }
    }

    private void calculateMargin() {
        int sbheight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            sbheight = getResources().getDimensionPixelSize(resourceId);
        }
        int dp10=(int)Utils.dp2px(this, 10);
        final int mtop=sbheight+(int)Utils.dp2px(this, 10);
        RelativeLayout.LayoutParams salp=(RelativeLayout.LayoutParams) mSearchArea.getLayoutParams();
        salp.setMargins(dp10, mtop, dp10, dp10);
        mSearchArea.setLayoutParams(salp);
        mSearchArea.post(() -> mFareView.post(() -> vMargin=new VMargin(mtop+mSearchArea.getHeight(), mFareView.getHeight())));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PLACE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                this.onPlaceSelected(place);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                this.onError(status);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNavigationFailed() {

    }


    @Override
    public void onError(Status status) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMaps = googleMap;
    }

    @Override
    public void GPSrequest() {

    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_down, R.anim.anim_push_down);
    }

    public void onBackPressed() {
        finishAction();
    }

    @Override
    public void gotoSelectMenu() {
        startActivity(new Intent(this, DeliveryActivity.class));
        finish();
    }
}
