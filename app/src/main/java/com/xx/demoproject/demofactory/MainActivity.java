package com.xx.demoproject.demofactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xx.demoproject.demofactory.customviews.RainbowBar;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private RainbowBar mRainbowBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
