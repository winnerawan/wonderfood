package net.winnerawan.wonderfood.ui.sign.up;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.helper.LatoEditText;
import net.winnerawan.wonderfood.ui.sign.in.SignInActivity;

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
public class SignUpActivity extends BaseActivity implements SignUpView {

    @Inject
    SignUpMvpPresenter<SignUpView> mPresenter;

    @BindView(R.id.txtName)
    LatoEditText txtName;
    @BindView(R.id.txtEmail)
    LatoEditText txtEmail;
    @BindView(R.id.txtPassword)
    LatoEditText txtPassword;

    @BindView(R.id.checkbox_tandc)
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_left, R.anim.anim_push_left);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.btnSignUp)
    void signUp() {
        mPresenter.signup(txtName.getText().toString(), txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim());
    }

    @OnClick(R.id.linkSignIn)
    void alreadyMember() {
        gotoSignInActivity();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void gotoSignInActivity() {
        finishAction();
    }

    @Override
    public boolean isAgreeTermConditions() {
        return mCheckBox.isChecked();
    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);
    }

    public void onBackPressed() {
        finishAction();
    }
}
