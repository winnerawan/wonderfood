package net.winnerawan.wonderfood.ui.home.account;

import android.content.Intent;
import android.os.Bundle;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.ui.base.BaseActivity;
import net.winnerawan.wonderfood.ui.home.account.about.AboutActivity;
import net.winnerawan.wonderfood.ui.home.account.privacypolicy.PrivacyPolicyActivity;
import net.winnerawan.wonderfood.ui.home.account.term.TermCondsActivity;
import net.winnerawan.wonderfood.ui.sign.in.SignInActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class AccountActivity extends BaseActivity implements AccountView {

    @Inject
    AccountMvpPresenter<AccountView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_down, R.anim.anim_push_down);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();
    }

    @OnClick(R.id.privacy_policy)
    void gotoPrivacyPolicy() {
        startActivity(new Intent(this, PrivacyPolicyActivity.class));
    }

    @OnClick(R.id.term_condition)
    void gotoTerm() {
        startActivity(new Intent(this, TermCondsActivity.class));
    }

    @OnClick(R.id.about)
    void gotoAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    @OnClick(R.id.img_close)
    void close() {
        finishAction();
    }

    @OnClick(R.id.txt_sign_out)
    void signOut() {
        mPresenter.signOut();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void gotoSignIn() {
        AccountActivity.this.finish();
        Intent i = new Intent(AccountActivity.this, SignInActivity.class);
        startActivity(i);
        finishAffinity();
        //overridePendingTransition(R.anim.anim_pop_right, R.anim.anim_push_right);

    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_up, R.anim.anim_push_up);
    }

    public void onBackPressed() {
        finishAction();
    }
}
