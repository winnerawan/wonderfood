package net.winnerawan.wonderfood.ui.home.order.place.qr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.hardware.camera2.CaptureResult;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.LatoTextView;
import net.winnerawan.wonderfood.ui.home.order.place.PlaceActivity;
import net.winnerawan.wonderfood.utils.AppLogger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.core.BarcodeScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class QrCodeActivity extends BaseActivity implements QrCodeView,
        ActivityCompat.OnRequestPermissionsResultCallback {

    @Inject
    QrCodeMvpPresenter<QrCodeView> mPresenter;

    @BindView(R.id.lyt_qr_reader)
    ViewGroup mLayout;

    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView mScannerView;

    private CaptureManager capture;
    private BeepManager beepManager;
    private static final int PERMISSION_REQUEST_CAMERA = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_up, R.anim.anim_push_up);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        capture = new CaptureManager(this, mScannerView);
        capture.initializeFromIntent(this.getIntent(), savedInstanceState);
        capture.decode();
        mScannerView.decodeSingle(mBarcodeCallback);
        beepManager = new BeepManager(this);
        setUp();
    }


    @Override
    public void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", view -> ActivityCompat.requestPermissions(QrCodeActivity.this, new String[] {
                            Manifest.permission.CAMERA
                    }, PERMISSION_REQUEST_CAMERA)).show();
        } else {
            Snackbar.make(mLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.CAMERA
            }, PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode != PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mPresenter.cameraPermissionGranted();
        } else {
            mPresenter.deniedCameraPermission();
        }
    }

    @Override
    protected void setUp() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestCameraPermission();
        }
    }

    private BarcodeCallback mBarcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText()==null) {
                return; //prevent duplicate scan
            }
            beepManager.playBeepSoundAndVibrate();
            mPresenter.initOrder(Integer.parseInt(result.getText()));
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    public void gotoSelectMenu() {
        startActivity(new Intent(this, PlaceActivity.class));
        finish();
    }

    private void finishAction() {
        capture.onDestroy();
        finish();
        overridePendingTransition(R.anim.anim_pop_down, R.anim.anim_push_down);
    }

    public void onBackPressed() {
        finishAction();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mScannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mScannerView.pause();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
