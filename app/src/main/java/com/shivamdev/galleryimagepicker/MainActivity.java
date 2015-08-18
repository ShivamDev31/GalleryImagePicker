package com.shivamdev.galleryimagepicker;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grid);

        addBucketFragment();

    }

    private void addBucketFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentBucket fb = FragmentBucket.newInstance();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.view_holder, fb, "bucket");
        ft.addToBackStack("bucket");
        ft.commit();
    }
}