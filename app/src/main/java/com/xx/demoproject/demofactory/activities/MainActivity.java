package com.xx.demoproject.demofactory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.retrofit.RetrofitDemo2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.misc_test_btn)
    Button miscTestBtn;
    @BindView(R.id.main_activity_btn1)
    Button mainActivityBtn1;
    @BindView(R.id.main_activity_btn2)
    Button mainActivityBtn2;
    @BindView(R.id.main_activity_btn3)
    Button mainActivityBtn3;
    @BindView(R.id.main_activity_btn4)
    Button mainActivityBtn4;
    @BindView(R.id.main_activity_btn5)
    Button mainActivityBtn5;
    @BindView(R.id.main_activity_btn6)
    Button mainActivityBtn6;
    @BindView(R.id.main_activity_btn7)
    Button mainActivityBtn7;
    @BindView(R.id.main_activity_btn8)
    Button mainActivityBtn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        miscTestBtn.setText("misc test");
        mainActivityBtn1.setText("RainbowBarActivity");
        mainActivityBtn2.setText("CustomViewGroupActivity");
        mainActivityBtn3.setText("SlotGameActivity");
        mainActivityBtn4.setText("SurfaceViewDemoActivity");
        mainActivityBtn5.setText("HandsupNotificationActivity");
        mainActivityBtn6.setText("flow window Demo");
        mainActivityBtn7.setText("blur view Demo");
        mainActivityBtn8.setText("butterknife Demo");
    }

    private void jumpToActivity(Class aimActivity) {
        Intent intent = new Intent();
        intent.setClass(this, aimActivity);
        startActivity(intent);
    }

    private void jumpToService(Class aimService) {
        Intent intent = new Intent();
        intent.setClass(this, aimService);
        startService(intent);
    }

    @OnClick({R.id.misc_test_btn, R.id.main_activity_btn1, R.id.main_activity_btn2, R.id.main_activity_btn3, R.id.main_activity_btn4, R.id.main_activity_btn5, R.id.main_activity_btn6, R.id.main_activity_btn7, R.id.main_activity_btn8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.misc_test_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //RetrofitDemo.doTest(1);
                        //RetrofitDemo2.doTest(RetrofitDemo2.TYPE_POST, RetrofitDemo2.TYPE_SYNC);
                        RetrofitDemo2.doTest(RetrofitDemo2.TYPE_POST, RetrofitDemo2.TYPE_ASYNC);
                        //GreenDaoDemo.doTest();
                        //OkHttpDemo.doTest(OkHttpDemo.TYPE_ASYNC_POST);
                    }
                }).start();
                break;
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
            case R.id.main_activity_btn5:
                jumpToActivity(HandsupNotificationActivity.class);
                break;
            case R.id.main_activity_btn6:
                jumpToService(FBService.class);
                break;
            case R.id.main_activity_btn7:
                jumpToActivity(BlurViewActivity.class);
                break;
            case R.id.main_activity_btn8:
                jumpToActivity(ButterKnifeDemoActivity.class);
                break;
            default:
                break;
        }
    }
}
