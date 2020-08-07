package com.android.p11ps;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class AboutUs extends AppCompatActivity {

    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
