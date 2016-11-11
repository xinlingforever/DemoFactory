package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.env.AppEnv;

import java.util.Random;

/**
 * Created by xx on 8/17/16.
 */
public class SingleSlotGameView extends View {

    private final String TAG = "SingleSlotGameView";

    //pic array
    private int[] mPicArray = {
            R.drawable.image_0,
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8
    };

    public final static int STATUS_RUNNING = 0;
    public final static int STATUS_STOPPING = 1;
    public final static int STATUS_STOPPED = 2;

    private int mCurStatus = STATUS_RUNNING;

    private Bitmap[] mBitmapArray = new Bitmap[mPicArray.length];

    //pic width
    private int mPicWidth = 0;
    //pic height
    private int mPicHeight = 0;
    //start point
    private float mStart = 0f;
    //speed
    private float mDelta = 10f;
    //current pic index
    private int mCurPicIndex = 0;

    private boolean mIsNeedStop = false;

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

        //draw first pic
        canvas.drawBitmap(mBitmapArray[fPicIndex], 0, mStart, null);
        //draw second pic
        canvas.drawBitmap(mBitmapArray[sPicIndex], 0, mStart-mPicHeight, null);
        //logout("mstart:" + mStart + " mDelta:"+mDelta);
        mStart += mDelta;
        if (mStart >= mPicHeight){
            mStart = 0;
            mCurPicIndex = sPicIndex;
        }
        logout("mIsNeedStop:"+mIsNeedStop+" mCurstatus:"+mCurStatus);
        if (mIsNeedStop == true && mCurStatus == STATUS_STOPPING) {
            mDelta--;
        }
        if (mDelta > 0 && mCurStatus != STATUS_STOPPED) {
            invalidate();
        }else{
            mCurStatus = STATUS_STOPPED;
            SlotGameGroupView parent = (SlotGameGroupView) getParent();
            parent.getChildAt(parent.indexOfChild(this)+SlotGameGroupView.CHILD_VIEW_NUM).setClickable(true);
            ((CustomBtnView)parent.getChildAt(parent.indexOfChild(this)+SlotGameGroupView.CHILD_VIEW_NUM)).setBtnText("START", Color.parseColor("black"));
            parent.getChildAt(parent.indexOfChild(this)+SlotGameGroupView.CHILD_VIEW_NUM).invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //logout("width:"+width+" height:"+height);

        mPicWidth = width;
        mPicHeight = height;

        Random random = new Random();
        //mDelta must be divisiable by mPicHeight
        mDelta = ((random.nextInt(9)+1)/10.0f) * mPicHeight;
        logout("mDelta:"+mDelta);

        //create pics
        for (int i=0; i<mPicArray.length; i++){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mPicArray[i]);
            bitmap = Bitmap.createScaledBitmap(bitmap, mPicWidth, mPicHeight, true);
            mBitmapArray[i] = bitmap;
        }

        setMeasuredDimension(mPicWidth, mPicHeight);
    }

    public void stop(){
        if (mCurStatus == STATUS_STOPPING || mCurStatus == STATUS_STOPPED){
            return;
        }
        mCurStatus = STATUS_STOPPING;
        SlotGameGroupView parent = (SlotGameGroupView) getParent();
        parent.getChildAt(parent.indexOfChild(this)+SlotGameGroupView.CHILD_VIEW_NUM).setClickable(false);
        setStopFlag(true);
    }

    public void start(){
        if (mCurStatus == STATUS_RUNNING || mCurStatus == STATUS_STOPPING){
            return;
        }
        mCurStatus = STATUS_RUNNING;
        setStopFlag(false);
        requestLayout();
        invalidate();
    }

    private void setStopFlag(boolean b){
        mIsNeedStop = b;
    }

    public int getCurStatus(){
        return mCurStatus;
    }

    public int getCurrentPicIndex(){
        return mCurPicIndex;
    }

    public float getStartPoint(){
        return mStart;
    }

    private void logout(final String trace){
        if (AppEnv.DEBUG) {
            Log.d(TAG, trace);
        }
    }
}
