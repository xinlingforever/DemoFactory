package com.xx.demoproject.demofactory.activities;

import android.app.Activity;
import android.os.Bundle;

import com.xx.demoproject.demofactory.customviews.MySurfaceView;

public class SurfaceViewDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}