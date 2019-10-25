package com.demo.hdt.searchcategory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonString = getStringJson();

        List<CategoryJson> categoryJsonList = parseJsonToObject(jsonString);
    }

    private List<CategoryJson> parseJsonToObject(String jsonString) {
        List<CategoryJson> categoryJsons = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<CategoryJson>>() {
            }.getType();
            categoryJsons = gson.fromJson(jsonString, listType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return categoryJsons;
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
}
