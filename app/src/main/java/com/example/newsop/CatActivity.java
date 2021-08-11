package com.example.newsop;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatActivity extends AppCompatActivity implements ClickCat{

    RecyclerView recyclerView;
    ArrayList<CAT> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        arr.add(new CAT(R.drawable.ic_baseline_business_24,"Business"));
        arr.add(new CAT(R.drawable.ic_baseline_theaters_24,"Entertainment"));
        arr.add(new CAT(R.drawable.heart,"Health"));
        arr.add(new CAT(R.drawable.ic_baseline_science_24,"Science"));
        arr.add(new CAT(R.drawable.ic_baseline_phone_iphone_24,"Technology"));
        arr.add(new CAT(R.drawable.ic_baseline_sports_cricket_24,"Sports"));


        recyclerView = findViewById(R.id.rv);

        CatAdap catAdap = new CatAdap(arr,CatActivity.this,CatActivity.this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setAdapter(catAdap);
    }

    @Override
    public void clickCat(String s) {
        Intent it = new Intent(CatActivity.this,MainActivity.class);
        it.putExtra("str",s);
        startActivity(it);
    }
}