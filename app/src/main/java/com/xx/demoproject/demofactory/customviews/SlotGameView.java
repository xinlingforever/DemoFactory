package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.env.AppEnv;

/**
 * Created by xx on 8/17/16.
 */
public class SlotGameView extends View {

    private final String TAG = "SlotGameView";

    private int[] mImageList = {
            R.drawable.image_0,
            R.drawable.image_1,
            R.drawable.image_2
    };

    //pic width
    private int mPicWidth = 0;
    //pic height
    private int mPicHeight = 0;
    //start point
    private int mStart = 0;
    //delta
    private int mDelta = 5;

    public SlotGameView(Context context) {
        super(context);
    }

    public SlotGameView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SlotGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.slotgame, 0, 0);
        mPicWidth = t.getDimensionPixelSize(
                R.styleable.ninephoto_ninephoto_hspace, mPicWidth);
        mPicHeight = t.getDimensionPixelSize(
                R.styleable.ninephoto_ninephoto_vspace, mPicHeight);
        t.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        logout("onDraw()");
        super.onDraw(canvas);
    }

    private void logout(final String trace){
        if (AppEnv.DEBUG) {
            Log.d(TAG, trace);
        }
    }
}
