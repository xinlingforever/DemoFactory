package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.AttributeSet;
import android.view.View;

import com.xx.demoproject.demofactory.R;

/**
 * Created by xx on 8/20/16.
 */
public class CustomBtnView extends View {

    private int mWidth = 80;
    private int mHeight = 80;

    private Paint mPaint;
    private Rect mRect;

    private float mTextFont = 60f;

    public CustomBtnView(Context context) {
        this(context, null, 0);
    }

    public CustomBtnView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBtnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("black"));
        //mPaint.setStrokeWidth(7);
        mPaint.setTextSize(mTextFont);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mWidth = width;
        mHeight = height;

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap btn = BitmapFactory.decodeResource(getResources(), R.drawable.switch_btn);
        //btn = Bitmap.createScaledBitmap(btn, mWidth, mHeight, true);

        NinePatch ninePatch = new NinePatch(btn, btn.getNinePatchChunk(), null);

        Rect rect = new Rect(0, 0, mWidth, mHeight);

        canvas.drawPatch(ninePatch, rect, null);

        String str = "STOP";
        int textLength = getTextViewRect(mPaint, str).width();
        int textHeight = getTextViewRect(mPaint, str).height();
        canvas.drawText(str, 0, str.length(), (mWidth-textLength)/2, (mHeight-textHeight)/2+textHeight, mPaint);
//        Rect r = new Rect((mWidth-textLength)/2, (mHeight-textHeight)/2, (mWidth-textLength)/2+getTextViewRect(mPaint, str).width(), (mHeight-textHeight)/2+getTextViewRect(mPaint, str).height());
//        canvas.drawRect(r, mPaint);

    }

    private Rect getTextViewRect(Paint paint, String text){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }
}
