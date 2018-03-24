package net.winnerawan.wonderfood.ui.home.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.db.model.History;
import net.winnerawan.wonderfood.ui.base.BaseViewHolder;
import net.winnerawan.wonderfood.ui.helper.LatoTextView;
import net.winnerawan.wonderfood.ui.helper.RecyclerViewAnimator;

import org.ocpsoft.prettytime.PrettyTime;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class HistoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<History> historys;
    private RecyclerViewAnimator mAnimator;
    private int lastPosition = -1;

    public HistoryAdapter(List<History> mHistorys) {
        historys = mHistorys;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history_empty, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (historys != null && historys.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (historys != null && historys.size() > 0) {
            return historys.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<History> historyList) {
        historys.addAll(historyList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onHistoryEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.status)
        TextView mStatus;
        @BindView(R.id.date)
        LatoTextView mDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mDate.setText("");
            mStatus.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final History history = historys.get(position);

            if (history.getStatus()==1) {
                mStatus.setText(itemView.getContext().getString(R.string.completed));
            } else {
                mStatus.setText(itemView.getContext().getString(R.string.on_progress));
            }
            if (history.getCreatedAt()!=null) {
                PrettyTime p = new PrettyTime();
                mDate.setText(p.format(java.sql.Timestamp.valueOf(history.getCreatedAt())));
            }

            itemView.setOnClickListener(view -> {
                if (history.getId()!=null) {
                    //todo
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }
    }


}
