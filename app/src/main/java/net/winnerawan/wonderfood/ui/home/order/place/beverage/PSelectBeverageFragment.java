package net.winnerawan.wonderfood.ui.home.order.place.beverage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.db.model.Menu;
import net.winnerawan.wonderfood.di.component.ActivityComponent;
import net.winnerawan.wonderfood.ui.base.BaseFragment;
import net.winnerawan.wonderfood.utils.AppLogger;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PSelectBeverageFragment extends BaseFragment implements PSelectBeverageView, PSelectBeverageAdapter.Callback, NumberPickerDialogFragment.NumberPickerDialogHandlerV2 {

    @Inject
    PSelectBeverageMvpPresenter<PSelectBeverageView> mPresenter;

    @Inject
    PSelectBeverageAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_explore)
    RecyclerView mRecyclerView;

    private int menu_id;
    private double menu_price;

    public static PSelectBeverageFragment newInstance() {
        Bundle args = new Bundle();
        PSelectBeverageFragment fragment = new PSelectBeverageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void showBeverages(List<Menu> menus) {
        mAdapter.addItems(menus);
    }

    @Override
    public void showDialog() {
        NumberPickerBuilder npb = new NumberPickerBuilder()
                .setFragmentManager(getActivity().getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setTargetFragment(PSelectBeverageFragment.this)
                .setLabelText(getString(R.string.portion));
        npb.show();
    }

    @Override
    public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
        if (!isNegative) {
            mPresenter.storeOrder(menu_id, Integer.parseInt(String.valueOf(number)),  Integer.parseInt(String.valueOf(number)) * menu_price);
        }
    }

    @Override
    public void getMenuId(int id, double price) {
        menu_id = id;
        menu_price = price;
    }
}
