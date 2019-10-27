package com.demo.hdt.searchcategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter {
    private OnClickCategoryListener onClickCategoryListener;
    private List<ItemCategory> itemCategoryList;

    public AdapterCategory(OnClickCategoryListener onClickCategoryListener) {
        this.onClickCategoryListener = onClickCategoryListener;
        itemCategoryList = new ArrayList<>();
    }

    public void setItemCategoryList(List<ItemCategory> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_category, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryVH categoryVH = (CategoryVH) holder;
        categoryVH.bindViews(position);
    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    public interface OnClickCategoryListener {
        void clickCategory(ItemCategory itemCategory);
    }

    private class CategoryVH extends RecyclerView.ViewHolder {
        private TextView tvName;

        private CategoryVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickCategoryListener.clickCategory(itemCategoryList.get(getAdapterPosition()));
                }
            });
        }

        private void bindViews(int position) {
            tvName.setText(itemCategoryList.get(position).getCategoryName());
        }
    }
}
