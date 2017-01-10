package com.xx.demoproject.demofactory.activities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.xx.demoproject.demofactory.R;

/**
 * Created by xx on 1/10/17.
 **/

public class FBService extends Service {

    private WindowManager wManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性

    private View windowView;
    private SurfaceHolder holder;
    public static final String ACTION_ALPHA = "com.fb.alpha";
    Button mBtn,mBtn2;


    @Override
    public void onCreate() {

        super.onCreate();
        wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;// 焦点
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;// 窗口的宽和高
        mParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        mParams.y = -25;
        mParams.x = 0;
        mParams.windowAnimations = android.R.style.Animation_Toast;
        // mParams.alpha = 0.8f;//窗口的透明度

        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        windowView = (View) layoutInflater.inflate(R.layout.float_view_layout, null);
        mBtn = (Button) windowView.findViewById(R.id.float_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xx", "pic in float view clicked");
            }
        });
        mBtn2 = (Button) windowView.findViewById(R.id.float_btn2);
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xx", "close float view");
                stopSelf();
            }
        });

        wManager.addView(windowView, mParams);// 添加窗口

    }

    @Override
    public void onDestroy() {
        if (wManager != null && windowView != null) {
            wManager.removeView(windowView);
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
