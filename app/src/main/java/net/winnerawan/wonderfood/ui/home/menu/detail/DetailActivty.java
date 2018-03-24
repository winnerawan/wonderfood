package net.winnerawan.wonderfood.ui.home.menu.detail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.LatoTextView;
import net.winnerawan.wonderfood.ui.home.order.delivery.DeliveryActivity;
import net.winnerawan.wonderfood.ui.home.order.place.qr.QrCodeActivity;
import net.winnerawan.wonderfood.utils.AppLogger;
import net.winnerawan.wonderfood.utils.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class DetailActivty extends BaseActivity implements DetailView {

    @Inject
    DetailMvpPresenter<DetailView> mPresenter;

    @BindView(R.id.name)
    TextView mTvName;
    @BindView(R.id.price)
    TextView mTvPrice;
    @BindView(R.id.image)
    CircleImageView mImageView;
    @BindView(R.id.lyt_img)
    LinearLayout mLinear;
    @BindView(R.id.action_order)
    FloatingActionMenu mActionOrder;
    @BindView(R.id.description)
    DocumentView mDocumentView;

    private FloatingActionButton fabDelivery;
    private FloatingActionButton fabPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();

        fabPlace.setOnClickListener(view -> {
            gotoScanQR();
        });

        fabDelivery.setOnClickListener(view -> {
            gotoDelivery();
        });
    }

    @Override
    protected void setUp() {
        initFloatingActionButton();
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Glide.with(this).load(bundle.getString("image")).into(mImageView);
            mTvName.setText(bundle.getString("name"));
            mTvPrice.setText(CommonUtils.priceFormater(Double.valueOf(bundle.getDouble("price")).longValue(), "Rp "));
            mDocumentView.setText(bundle.getString("description"));
            Glide.with(this).load(bundle.getString("image")).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(getApplicationContext().getResources(), resource);
                    mLinear.setBackground(drawable);
                }
            });
        }

    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
    }

    public void onBackPressed() {
        finishAction();
    }

    @Override
    public void initFloatingActionButton() {
        fabPlace =  new FloatingActionButton(this);
        fabDelivery = new FloatingActionButton(this);
        fabPlace.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabDelivery.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabPlace.setLabelText(getString(R.string.action_place_order));
        fabDelivery.setLabelText(getString(R.string.action_delivery_order));
        fabPlace.setColorNormal(ContextCompat.getColor(getApplicationContext(), R.color.light_green));
        fabDelivery.setColorNormal(ContextCompat.getColor(getApplicationContext(), R.color.light_green));
        fabPlace.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fab_add));
        fabDelivery.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fab_add));
        mActionOrder.addMenuButton(fabPlace);
        mActionOrder.addMenuButton(fabDelivery);
    }

    @Override
    public void gotoScanQR() {
        startActivity(new Intent(this, QrCodeActivity.class));
    }

    @Override
    public void gotoDelivery() {
        startActivity(new Intent(this, DeliveryActivity.class));
    }
}
