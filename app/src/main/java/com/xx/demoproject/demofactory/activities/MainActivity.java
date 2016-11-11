package com.xx.demoproject.demofactory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xx.demoproject.demofactory.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn1, mBtn2, mBtn3, mBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn1 = (Button) findViewById(R.id.main_activity_btn1);
        mBtn1.setText("RainbowBarActivity");
        mBtn1.setOnClickListener(this);

        mBtn2 = (Button) findViewById(R.id.main_activity_btn2);
        mBtn2.setText("CustomViewGroupActivity");
        mBtn2.setOnClickListener(this);

        mBtn3 = (Button) findViewById(R.id.main_activity_btn3);
        mBtn3.setText("SlotGameActivity");
        mBtn3.setOnClickListener(this);

        mBtn4 = (Button) findViewById(R.id.main_activity_btn4);
        mBtn4.setText("SurfaceViewDemoActivity");
        mBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_activity_btn1:
                jumpToActivity(RainbowBarActivity.class);
                break;
            case R.id.main_activity_btn2:
                jumpToActivity(CustomViewGroupActivity.class);
                break;
            case R.id.main_activity_btn3:
                jumpToActivity(SlotGameActivity.class);
                break;
            case R.id.main_activity_btn4:
                jumpToActivity(SurfaceViewDemoActivity.class);
                break;
            default:
                break;
        }
    }

    private void jumpToActivity(Class aimActivity){
        Intent intent = new Intent();
        intent.setClass(this, aimActivity);
        startActivity(intent);
    }
}
