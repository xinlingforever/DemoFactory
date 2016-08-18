package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.env.AppEnv;

import java.util.Random;

/**
 * Created by xx on 8/17/16.
 */
public class SingleSlotGameView extends View {

    private final String TAG = "SingleSlotGameView";

    public static int slotSpeed = 40;

    //pic array
    private int[] mPicArray = {
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6
    };

    //pic width
    private int mPicWidth = 0;
    //pic height
    private int mPicHeight = 0;
    //start point
    private int mStart = 0;
    //speed
    private int mDelta = 10;
    //current pic index
    private int mCurPicIndex = 0;

    public SingleSlotGameView(Context context) {
        super(context);
    }

    public SingleSlotGameView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SingleSlotGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //logout("onDraw() width:"+canvas.getWidth()+" height:"+canvas.getHeight());
        super.onDraw(canvas);
        int fPicIndex = mCurPicIndex;
        int sPicIndex = (mCurPicIndex - 1) >= 0 ? (mCurPicIndex - 1) : (mPicArray.length - 1);

        Bitmap firstBitmap = BitmapFactory.decodeResource(getResources(), mPicArray[fPicIndex]);
        Bitmap secBitmap = BitmapFactory.decodeResource(getResources(), mPicArray[sPicIndex]);

        firstBitmap = Bitmap.createScaledBitmap(firstBitmap, mPicWidth, mPicHeight, true);
        secBitmap = Bitmap.createScaledBitmap(secBitmap, mPicWidth, mPicHeight, true);

        //draw first pic
        canvas.drawBitmap(firstBitmap, 0, mStart, null);
        //draw second pic
        canvas.drawBitmap(secBitmap, 0, mStart-mPicHeight, null);

        mStart += mDelta;
        if (mStart >= mPicHeight){
            mStart = 0;
            mCurPicIndex = sPicIndex;
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        logout("width:"+width+" height:"+height);

        mPicWidth = width;
        mPicHeight = height;

        Random random = new Random();
        if (slotSpeed > mPicHeight || slotSpeed <= 0) {
            mDelta = random.nextInt(mPicHeight);
        }else {
            mDelta = random.nextInt(slotSpeed);
        }

        logout("mDelta:"+mDelta);

        setMeasuredDimension(mPicWidth, mPicHeight);
    }

    private void logout(final String trace){
        if (AppEnv.DEBUG) {
            Log.d(TAG, trace);
        }
    }
}
