package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.AttributeSet;
import android.view.View;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.Utils.DensityUtil;
import com.xx.demoproject.demofactory.env.AppEnv;

/**
 * Created by xx on 8/13/16.
 */
public class RainbowBar extends View {

    protected static final boolean DEBUG = AppEnv.DEBUG;
    protected static final String TAG = "RainbowBar";

    private boolean mIsStop = false;

    private int[] COLOR_ARRAY = {
            Color.parseColor("red"),
            Color.parseColor("yellow"),
            Color.parseColor("green"),
            Color.parseColor("gray"),
            Color.parseColor("blue"),
            Color.parseColor("purple"),
            Color.parseColor("cyan")};

    private int mResIndexLatter = 0;
    private int mBarColor = Color.parseColor("#1E88E5");
    private int mHSpace = DensityUtil.dip2px(mContext, 80);
    private int mVSpace = DensityUtil.dip2px(mContext, 4);
    private int mSpace = DensityUtil.dip2px(mContext, 10);

    private float mStartX = 0;
    private float mDelta = 10f;
    private Paint mPaint;

    public RainbowBar(Context context) {
        super(context);
    }

    public RainbowBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainbowBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.rainbowbar, 0, 0);
        mHSpace = typedArray.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, mHSpace);
        mVSpace = typedArray.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, mVSpace);
        mBarColor = typedArray.getColor(R.styleable.rainbowbar_rainbowbar_color, mBarColor);
        logout("H:"+DensityUtil.px2dip(context, mHSpace)+" V:"+DensityUtil.px2dip(context, mVSpace)+" B:"+mBarColor);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBarColor);
        mPaint.setStrokeWidth(mVSpace);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        logout("onDraw()");
        //Nothing to in super class
        super.onDraw(canvas);
        //get screen width
        float sw = this.getMeasuredWidth();
        logout("getMeasuredWidth:"+sw+" mStartX:"+mStartX+" mHSpace:"+mHSpace+" mSpace:"+mSpace+" mDelta:"+mDelta);

        if (mStartX >= sw + (mHSpace + mSpace) - (sw % (mHSpace + mSpace))) {
            mStartX = 0;
            mResIndexLatter += 1;
            if (mResIndexLatter > COLOR_ARRAY.length - 1){
                mResIndexLatter = 0;
            }
        } else {
            mStartX += mDelta;
        }
        float start = mStartX;

        // draw latter parse
        int index = mResIndexLatter;
        while (start < sw) {
            mPaint.setColor(COLOR_ARRAY[index]);
            index++;
            if (index >= COLOR_ARRAY.length){
                index = 0;
            }
            logout("start:"+start+" sw:"+sw);
            canvas.drawLine(start, 5, start + mHSpace, 5, mPaint);
            start += (mHSpace + mSpace);
        }
        logout("after latter parse, start:"+start+" mStartX:"+mStartX);
        start = mStartX - mSpace - mHSpace;

        // draw front parse
        index = mResIndexLatter - 1;
        while (start >= -mHSpace) {
            if (index < 0){
                index = COLOR_ARRAY.length - 1;
            }
            mPaint.setColor(COLOR_ARRAY[index]);
            index--;
            canvas.drawLine(start, 5, start + mHSpace, 5, mPaint);
            start -= (mHSpace + mSpace);
        }

        //refresh onDraw()
        if (!mIsStop) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        logout("onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        logout("onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void logout(final String trace){
        if (DEBUG){
            Log.d(TAG, trace);
        }
    }

    public void setStopFlag(final boolean isStop) {
        mIsStop = isStop;
    }

    public boolean getStopFlag(){
        return mIsStop;
    }
}
