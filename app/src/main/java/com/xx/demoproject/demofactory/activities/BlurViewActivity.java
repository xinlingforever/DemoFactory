package com.xx.demoproject.demofactory.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.xx.demoproject.demofactory.R;

import jp.wasabeef.blurry.Blurry;

public class BlurViewActivity extends Activity {

    private final String TAG = "BlurViewActivity";

    Button mBtn1, mBtn2;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_view);

        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Blurry.with(BlurViewActivity.this)
                        .radius(25)
                        .sampling(1)
                        //.color(Color.argb(66, 0, 255, 255))
                        .async()
                        .capture(findViewById(R.id.image))
                        .into((ImageView) findViewById(R.id.image));
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {

            private boolean blurred = false;

            @Override public void onClick(View v) {
                if (blurred) {
                    hideAndDelete((ViewGroup) findViewById(R.id.content), 1000);
                } else {
                    Blurry.with(BlurViewActivity.this)
                            .radius(25)
                            .sampling(2)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) findViewById(R.id.content));
                }

                blurred = !blurred;
            }
        });

        findViewById(R.id.image).setOnLongClickListener(new View.OnLongClickListener() {

            private boolean blurred = false;

            @Override
            public boolean onLongClick(View v) {
                if (blurred) {
                    hideAndDelete((ViewGroup) findViewById(R.id.content), 1000);
                } else {
                    Blurry.with(BlurViewActivity.this)
                            .radius(25)
                            .sampling(2)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) findViewById(R.id.content));
                }

                blurred = !blurred;
                return true;
            }
        });

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
