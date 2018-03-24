package net.winnerawan.wonderfood.ui.home.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.db.model.Menu;
import net.winnerawan.wonderfood.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class SearchActivity extends BaseActivity implements SearchView {

    @Inject
    SearchMvpPresenter<SearchView> mPresenter;

    @Inject
    SearchAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recyclerview_search)
    RecyclerView mRecyclerView;

    @BindView(R.id.txt_search)
    EditText mSearch;

    @BindView(R.id.result)
    TextView mResult;

    @BindView(R.id.result_search)
    TextView mResultSearch;

    private List<Menu> mMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getActivityComponent().inject(this);
        overridePendingTransition(R.anim.anim_pop_down, R.anim.anim_push_down);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        setUp();

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mMenus!=null) {
                    final List<Menu> filteredMenus = filterMenu(mMenus, mSearch.getText().toString().toLowerCase());
                    mAdapter = new SearchAdapter(filteredMenus);
                    mRecyclerView.setAdapter(mAdapter);
                    mResult.setText(filteredMenus.size()+" "+getString(R.string.result_for));
                    mResultSearch.setText(" "+mSearch.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.img_close)
    void close() {
        finishAction();
    }

    private void finishAction() {
        finish();
        overridePendingTransition(R.anim.anim_pop_up, R.anim.anim_push_up);
    }

    public void onBackPressed() {
        finishAction();
    }

    @Override
    protected void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.onViewPrepared();
    }

    @Override
    public void showMenus(List<Menu> menus) {
        mMenus = menus;
        mAdapter.addItems(menus);
    }

    @Override
    public List<Menu> filterMenu(List<Menu> menus, String query) {
        query = query.toLowerCase();
        final List<Menu> filteredModelList = new ArrayList<>();
        for (Menu model : menus) {
            final String name = model.getName().toLowerCase();
            final String price = String.valueOf(model.getPrice()).toLowerCase();
            if (name.contains(query) || price.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
