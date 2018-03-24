package net.winnerawan.wonderfood.ui.home.history.complete;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.db.model.History;
import net.winnerawan.wonderfood.di.component.ActivityComponent;
import net.winnerawan.wonderfood.ui.base.BaseFragment;
import net.winnerawan.wonderfood.ui.home.history.HistoryAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class HistoryCompleteFragment extends BaseFragment implements HistoryCompleteView {

    @Inject
    HistoryCompleteMvpPresenter<HistoryCompleteView> mPresenter;

    @Inject
    HistoryAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_history)
    RecyclerView mRecyclerView;

    public static HistoryCompleteFragment newInstance() {
        Bundle args = new Bundle();
        HistoryCompleteFragment fragment = new HistoryCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            //mAdapter.setCallback(this);
        }
        return view;
    }
    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadCompleteHistory();
    }

    @Override
    public void showHistory(List<History> history) {
        mAdapter.addItems(history);
    }
}
