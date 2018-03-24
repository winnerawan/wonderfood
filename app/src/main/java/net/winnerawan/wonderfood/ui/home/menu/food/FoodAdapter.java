package net.winnerawan.wonderfood.ui.home.menu.food;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.winnerawan.wonderfood.R;
import net.winnerawan.wonderfood.data.db.model.Menu;
import net.winnerawan.wonderfood.ui.base.BaseViewHolder;
import net.winnerawan.wonderfood.ui.helper.LatoTextView;
import net.winnerawan.wonderfood.ui.helper.RecyclerViewAnimator;
import net.winnerawan.wonderfood.ui.home.menu.detail.DetailActivty;

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
public class FoodAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Menu> menus;
    private RecyclerViewAnimator mAnimator;
    private int lastPosition = -1;

    public FoodAdapter(List<Menu> mMenus) {
        //mAnimator = new RecyclerViewAnimator(mRecyclerView);
        menus = mMenus;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);

//        //mAnimator.onBindViewHolder(holder.itemView, position);
//        if(position >lastPosition) {
//
//            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),
//                    R.anim.item_animation_fall_down);
//            holder.itemView.startAnimation(animation);
//            lastPosition = position;
//        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_empty, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (menus != null && menus.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (menus != null && menus.size() > 0) {
            return menus.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Menu> menuList) {
        menus.addAll(menuList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onMenuEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView mImageView;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.price)
        LatoTextView mPrice;
        @BindView(R.id.timetext)
        LatoTextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mImageView.setImageDrawable(null);
            mTitle.setText("");
            mPrice.setText("");
            mTime.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Menu menu = menus.get(position);
            String test = "https://static.pexels.com/photos/70497/pexels-photo-70497.jpeg";

            if (menu.getImage()!=null) {
                try {
                    Glide.with(itemView.getContext())
                            .load(new URL(menu.getImage()))
                            .into(mImageView);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            if (menu.getName()!=null) {
                mTitle.setText(menu.getName());
                mPrice.setText(String.valueOf(menu.getPrice()));
            }

            itemView.setOnClickListener(view -> {
                if (menu.getId()!=null) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivty.class);
                    intent.putExtra("name", menu.getName());
                    intent.putExtra("price", menu.getPrice());
                    intent.putExtra("description", menu.getDescription());
                    intent.putExtra("image", menu.getImage());
                    itemView.getContext().startActivity(intent);
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
