package net.winnerawan.wonderfood.ui.sign.in;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.wang.avi.AVLoadingIndicatorView;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.LatoEditText;
import net.winnerawan.wonderfood.ui.helper.LatoTextView;
import net.winnerawan.wonderfood.ui.home.HomeActivity;
import net.winnerawan.wonderfood.ui.sign.up.SignUpActivity;
import net.winnerawan.wonderfood.utils.AppLogger;

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
public class SignInActivity extends BaseActivity implements SignInView {

    @Inject
    SignInMvpPresenter<SignInView> mPresenter;

    @BindView(R.id.txtEmail)
    LatoEditText txtEmail;
    @BindView(R.id.txtPassword)
    LatoEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.btnSignIn)
    void onUserSignIn() {
        mPresenter.authentication(txtEmail.getText().toString(), txtPassword.getText().toString());
    }

    @OnClick(R.id.linkSignUp)
    void newUser() {
        gotoSignUpActivity();
    }

    @Override
    public void gotoHomeActivity() {
        Intent i = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void gotoSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    protected void setUp() {

    }


//
//    private void finishAction() {
//        finish();
//        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
//    }
//
//    public void onBackPressed() {
//        finishAction();
//    }
}
