package com.xx.demoproject.demofactory.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.xx.demoproject.demofactory.R;

import jp.wasabeef.blurry.Blurry;

import static java.lang.Math.abs;

/**
 * Created by xx on 1/10/17.
 **/

public class FBService extends Service {

    private final String TAG = "FBService";
    private WindowManager mWindowManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性

    private View windowView;
    Button mBtn1, mBtn2;

    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;


    @Override
    public void onCreate() {

        super.onCreate();

        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_IMMERSIVE;//隐藏Navigation Bar
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        windowView = inflater.inflate(R.layout.activity_blur_view, null);
        windowView.setSystemUiVisibility(flags);
        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams(-1, -1,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, //让window占满整个手机屏幕，不留任何边界（border）
                PixelFormat.TRANSLUCENT);
        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.y = 0;
        mParams.x = 0;
        mWindowManager.addView(windowView, mParams);
        mBtn1 = (Button) windowView.findViewById(R.id.btn1);
        mBtn2 = (Button) windowView.findViewById(R.id.btn2);
        mBtn1.setOnClickListener(v -> Blurry.with(FBService.this)
                .radius(25)
                .sampling(1)
                //.color(Color.argb(66, 0, 255, 255))
                .async()
                .capture((ImageView) windowView.findViewById(R.id.image))
                .into((ImageView) windowView.findViewById(R.id.image)));

        mBtn2.setOnClickListener(new View.OnClickListener() {

            private boolean blurred = false;

            @Override public void onClick(View v) {
                if (blurred) {
                    hideAndDelete((ViewGroup) windowView.findViewById(R.id.viewgroup), 1000);
                } else {
                    Blurry.with(FBService.this)
                            .radius(25)
                            .sampling(2)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) windowView.findViewById(R.id.viewgroup));
                }

                blurred = !blurred;
            }
        });


        windowView.findViewById(R.id.viewgroup).setOnLongClickListener(new View.OnLongClickListener() {

            private boolean blurred = false;

            @Override
            public boolean onLongClick(View v) {
                if (blurred) {
                    hideAndDelete((ViewGroup) windowView.findViewById(R.id.viewgroup), 1000);
                } else {
                    Blurry.with(FBService.this)
                            .radius(25)
                            .sampling(2)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) windowView.findViewById(R.id.viewgroup));
                }

                blurred = !blurred;
                return true;
            }
        });


        windowView.setOnTouchListener((v, event) -> {
            //获取相对屏幕的坐标，即以屏幕左上角为原点
            x = event.getRawX();
            y = event.getRawY();   //25是系统状态栏的高度
            //Log.i(TAG, "currX" + x + "====currY" + y);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                    //获取相对View的坐标，即以此View左上角为原点
                    Log.i(TAG, " startX=" + mTouchStartX + " startY=" + mTouchStartY);
                    mTouchStartX = event.getX();
                    mTouchStartY = event.getY();
                    Log.i(TAG, " getX=" + event.getX() + " getY=" + event.getY());

                    break;
                case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作
                    updateViewPosition();
                    break;
                case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                    updateViewPosition();
                    mTouchStartX = mTouchStartY = 0;
                    break;
            }
            return true;
        });
    }

    private void updateViewPosition() {
        //更新浮动窗口位置参数
        //Log.d(TAG, "from x:"+mParams.x+" y:"+mParams.y+" to x:"+(x-mTouchStartX)+" y:"+(y-mTouchStartY));
        Log.d(TAG, "mParams.x:"+mParams.x+" x:"+x+" mTouchStartX:"+mTouchStartX);
//        mParams.x = (int)x; //(int) (x - mTouchStartX);
//        mParams.y = (int)y; //(int) (y - mTouchStartY);
//        mWindowManager.updateViewLayout(view, mParams);  //刷新显示
        if ((abs(x-mTouchStartX) > 600) || (abs(y-mTouchStartY) > 600)) {
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        if (mWindowManager != null && windowView != null) {
            mWindowManager.removeView(windowView);
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void hideAndDelete(ViewGroup viewGroup, int duration) {
        View view = viewGroup.findViewWithTag(Blurry.class.getSimpleName());
        if (view != null) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);

            anim.setDuration(duration);// 动画持续时间
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    Log.d(TAG, "value:"+value);
                    if (value == 0) {
                        viewGroup.removeView(view);
                    }
                }
            });
            anim.start();
        }
    }
}
