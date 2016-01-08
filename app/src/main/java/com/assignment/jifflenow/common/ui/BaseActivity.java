package com.assignment.jifflenow.common.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.assignment.jifflenow.R;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout mBaseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mBaseContainer = (FrameLayout) findViewById(R.id.base_container);
    }


    protected void setLayout(int layoutId) {
        getLayoutInflater().inflate(layoutId, mBaseContainer);

    }
}
