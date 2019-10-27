package com.demo.hdt.searchcategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterParent extends RecyclerView.Adapter {
    private OnClickParentListener onClickParentListener;
    private List<ItemCategory> itemCategoryList;

    public AdapterParent(OnClickParentListener onClickParentListener) {
        this.onClickParentListener = onClickParentListener;
        itemCategoryList = new ArrayList<>();
    }

    public void addItemCategory(ItemCategory itemCategory) {
        itemCategoryList.add(itemCategory);
    }

    public void addItemCategoryList(List<ItemCategory> itemCategoryList) {
        itemCategoryList.addAll(itemCategoryList);
    }

    public List<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(List<ItemCategory> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public ItemCategory getLastItemCategoryList() {
        return itemCategoryList.get(itemCategoryList.size() - 1);
    }

    public void removeItemCategorySaveLast(ItemCategory itemCategory) {
        int indexLast = itemCategoryList.indexOf(itemCategory);
        itemCategoryList = itemCategoryList.subList(0, indexLast + 1);
    }

    public void removeItemCategory(ItemCategory itemCategory) {
        int indexLast = itemCategoryList.indexOf(itemCategory);
        itemCategoryList = itemCategoryList.subList(0, indexLast);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_parent, parent, false);
        return new ParentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ParentVH parentVH = (ParentVH) holder;
        parentVH.bindViews(position);
    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    public interface OnClickParentListener {
        void clickParent(ItemCategory itemCategory);
    }

    private class ParentVH extends RecyclerView.ViewHolder {
        private TextView tvName;

        private ParentVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickParentListener.clickParent(itemCategoryList.get(getAdapterPosition()));
                }
            });
        }

        private void bindViews(int position) {
            tvName.setText(itemCategoryList.get(position).getCategoryName());
        }
    }
}
