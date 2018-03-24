package net.winnerawan.wonderfood.ui.helper;

import android.util.AttributeSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.graphics.Paint;

import com.google.android.gms.maps.*;

import android.widget.RadioButton;
import android.widget.CompoundButton;

import net.winnerawan.wonderfood.R;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */

public class FareView extends FrameLayout implements CompoundButton.OnCheckedChangeListener {

    TextView jarak, oldprice, lowprice, normprice, reload;
    private View v, myLoc, jarakBar, rootv;
    public static int myHeight = 0;
    public static int[] jarakBarHeight = new int[2];
    private RadioButton rb1, rb2;
    private GoogleMap.OnMyLocationButtonClickListener callmyloc;

    public FareView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public FareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FareView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        v = LayoutInflater.from(getContext()).inflate(R.layout.fare_view, null);
        jarak = v.findViewById(R.id.tariffviewJarak);
        oldprice = v.findViewById(R.id.tariffviewOldPrice);
        normprice = v.findViewById(R.id.tariffviewNormalPrice);
        lowprice = v.findViewById(R.id.tariffviewLowPrice);
        reload = v.findViewById(R.id.tariffviewReload);
        rb1 = v.findViewById(R.id.sbRadioButton1);
        rb2 = v.findViewById(R.id.sbRadioButton2);
        jarakBar = v.findViewById(R.id.tariffviewLinearLayout1);
        myLoc = v.findViewById(R.id.tariffviewMyLoc);
        rootv = v.findViewById(R.id.tariffviewLinearLayout2);
        oldprice.setPaintFlags(oldprice.getPaintFlags());
        myLoc.setOnClickListener(p1 -> {
            if (callmyloc != null) callmyloc.onMyLocationButtonClick();
        });
        post(() -> myHeight = getHeight());
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb2.setChecked(true);
        addView(v);
    }

    @Override
    public void onCheckedChanged(CompoundButton p1, boolean p2) {
        if (rb1 == p1 && p2) {
            rb2.setChecked(false);
        } else if (rb2 == p1 && p2) {
            rb1.setChecked(false);
        }
    }

    public void setJarak(String s) {
        jarak.setText(s);
    }

    public void setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener x) {
        callmyloc = x;
    }

    public void hide(final boolean animate) {
        rootv.post(() -> {
            animate().setStartDelay(0);
            animate().setDuration(animate ? 500 : 0);
            animate().translationY(rootv.getHeight());
            animate().start();
        });
    }

    public void show() {
        animate().setStartDelay(1000);
        animate().setDuration(1500);
        animate().translationY(0);
        animate().start();
    }

    public void setTarifByJarak(double d) {
        long tarif = 0;
        long tarifdisc = 0;
        if (d <= 3) {
            tarif = 6000;
            tarifdisc = tarif - 1000;
        } else {
            tarif = Math.round(((d - 3) * 2000) + 6000);
            tarifdisc = tarif - (tarif > 20000 ? 10000 : 1000);
        }
        long roundedTarif = ((tarif + 99) / 100) * 100;
        long test = ((20000 + 99 ) / 100 ) * 100;
        long roundedTarifdisc = ((tarifdisc + 99) / 100) * 100;
        normprice.setText(priceFormater(test, "Rp"));
        oldprice.setText(priceFormater(roundedTarif, "Rp"));
        lowprice.setText(priceFormater(roundedTarifdisc, "Rp"));
    }

    public String priceFormater(long s, String currency) {
        return (currency + s).replaceAll("(\\d)(?=(\\d{3})+(?!\\d))", "$1.");
    }
}
