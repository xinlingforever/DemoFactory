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

/**
 * Created by xx on 8/13/16.
 */
public class RainbowBar extends View {

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
        Log.d("xx","H:"+DensityUtil.px2dip(context, mHSpace)+" V:"+DensityUtil.px2dip(context, mVSpace)+" B:"+mBarColor);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBarColor);
        mPaint.setStrokeWidth(mVSpace);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Log.d("xx", "onDraw()");
        super.onDraw(canvas);
        //get screen width
        float sw = this.getMeasuredWidth();
        if (mStartX >= sw + (mHSpace + mSpace) - (sw % (mHSpace + mSpace))) {
            mStartX = 0;
        } else {
            mStartX += mDelta;
        }
        float start = mStartX;
        // draw latter parse
        while (start < sw) {
            canvas.drawLine(start, 5, start + mHSpace, 5, mPaint);
            start += (mHSpace + mSpace);
        }

        start = mStartX - mSpace - mHSpace;

        // draw front parse
        while (start >= -mHSpace) {
            canvas.drawLine(start, 5, start + mHSpace, 5, mPaint);
            start -= (mHSpace + mSpace);
        }
        //refresh onDraw()
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        //Log.d("xx","onLayout()");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Log.d("xx","onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
