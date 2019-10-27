package com.demo.hdt.searchcategory;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterParent.OnClickParentListener, AdapterCategory.OnClickCategoryListener {
    private DBManager dbManager;
    private AdapterParent adapterParent;
    private AdapterCategory adapterCategory;
    private TextView tvNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNoti = findViewById(R.id.tv_noti);

        dbManager = new DBManager(this);
        if (!dbManager.isDatabaseExists()) {
            String jsonString = getStringJson();

            List<CategoryJson> categoryJsonList = parseJsonToObject(jsonString);

            dbManager.insertListCategoryJson(categoryJsonList);
        }

        RecyclerView rcvParent = findViewById(R.id.rcv_parent);
        rcvParent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterParent = new AdapterParent(this);
        adapterParent.addItemCategory(
                new ItemCategory(null, "Tất cả", 0, null, null));
        rcvParent.setAdapter(adapterParent);

        RecyclerView rcvCategory = findViewById(R.id.rcv_category);
        rcvCategory.setLayoutManager(new LinearLayoutManager(this));

        adapterCategory = new AdapterCategory(this);
        List<ItemCategory> itemCategoryList = dbManager.getItemCategoryListByParentIdAndGroup(
                adapterParent.getLastItemCategoryList().getCategoryId(),
                adapterParent.getLastItemCategoryList().getCategoryGroup() + 1);
        adapterCategory.setItemCategoryList(itemCategoryList);

        rcvCategory.setAdapter(adapterCategory);

        if (itemCategoryList.size() != 0) {
            tvNoti.setVisibility(View.GONE);
        } else {
            tvNoti.setVisibility(View.VISIBLE);
        }

        EditText edtSearch = findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<ItemCategory> itemCategoryList = dbManager.searchItemCategoryListByNameAndParentId(
                        s.toString().trim(),
                        adapterParent.getLastItemCategoryList().getCategoryId());
                adapterCategory.setItemCategoryList(itemCategoryList);
                adapterCategory.notifyDataSetChanged();

                tvNoti.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private List<CategoryJson> parseJsonToObject(String jsonString) {
        List<CategoryJson> categoryJsonList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<CategoryJson>>() {
            }.getType();
            categoryJsonList = gson.fromJson(jsonString, listType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return categoryJsonList;
    }

    private String getStringJson() {
        String jsonString;

        Writer writer = new StringWriter();
        try (InputStream is = getResources().openRawResource(R.raw.category)) {
            char[] buffer = new char[1024];
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonString = writer.toString();
        return jsonString;
    }

    @Override
    public void clickParent(ItemCategory itemCategory) {
        List<ItemCategory> itemCategoryList = dbManager.getItemCategoryListByParentIdAndGroup(
                itemCategory.getCategoryId(),
                itemCategory.getCategoryGroup() + 1);
        adapterCategory.setItemCategoryList(itemCategoryList);
        adapterCategory.notifyDataSetChanged();

        if (itemCategoryList.size() != 0) {
            tvNoti.setVisibility(View.GONE);
        } else {
            tvNoti.setVisibility(View.VISIBLE);
        }

        adapterParent.removeItemCategorySaveLast(itemCategory);
        adapterParent.notifyDataSetChanged();
    }

    @Override
    public void clickCategory(ItemCategory itemCategory) {
        List<ItemCategory> itemCategoryList = dbManager.getItemCategoryListByParentIdAndGroup(
                itemCategory.getCategoryId(),
                itemCategory.getCategoryGroup() + 1);
        adapterCategory.setItemCategoryList(itemCategoryList);
        adapterCategory.notifyDataSetChanged();

        if (itemCategoryList.size() != 0) {
            tvNoti.setVisibility(View.GONE);
        } else {
            tvNoti.setVisibility(View.VISIBLE);
        }

        adapterParent.addItemCategory(itemCategory);
        adapterParent.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (adapterParent.getItemCategoryList().size() > 1) {
            ItemCategory itemCategory = adapterParent.getLastItemCategoryList();

            List<ItemCategory> itemCategoryList = dbManager.getItemCategoryListByParentId(
                    itemCategory.getRankParentId());
            adapterCategory.setItemCategoryList(itemCategoryList);
            adapterCategory.notifyDataSetChanged();

            if (itemCategoryList.size() != 0) {
                tvNoti.setVisibility(View.GONE);
            } else {
                tvNoti.setVisibility(View.VISIBLE);
            }

            adapterParent.removeItemCategory(itemCategory);
            adapterParent.notifyDataSetChanged();
        } else {
            super.onBackPressed();
        }
    }
}
