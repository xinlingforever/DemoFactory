package com.xx.demoproject.demofactory.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.customviews.RainbowBar;

/**
 * CustomerView Demo
 *
 * */

public class RainbowBarActivity extends Activity {

    private Button mBtn;
    private RainbowBar mRainbowBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_bar);

        mRainbowBar = (RainbowBar) findViewById(R.id.ranbowbar);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRainbowBar.setStopFlag(!mRainbowBar.getStopFlag());
                if (mRainbowBar.getStopFlag() == false){
                    mRainbowBar.invalidate();
                    mBtn.setText("STOP");
                }else{
                    mBtn.setText("GO");
                }
            }
        });
    }

}
