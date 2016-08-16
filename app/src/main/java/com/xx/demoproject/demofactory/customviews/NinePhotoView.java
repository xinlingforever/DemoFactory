package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.env.AppEnv;
import com.xx.demoproject.demofactory.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 8/16/16.
 */
public class NinePhotoView extends ViewGroup {

    protected static final String TAG = "NinePhotoView";

    public static final int MAX_PHOTO_NUMBER = 9;

    private int[] constImageIds = {
            R.drawable.image_0,
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8 };

    // horizontal space among children views
    int hSpace = DensityUtil.dip2px(mContext, 10);
    // vertical space among children views
    int vSpace = DensityUtil.dip2px(mContext, 10);

    // every child view width and height.
    int childWidth = 0;
    int childHeight = 0;

    // store images res id
    List<Integer> mImageResArrayList = new ArrayList<Integer>(9);
    private View addPhotoView;

    public NinePhotoView(Context context) {
        super(context);
    }

    public NinePhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NinePhotoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.ninephoto, 0, 0);
        hSpace = t.getDimensionPixelSize(
                R.styleable.ninephoto_ninephoto_hspace, hSpace);
        vSpace = t.getDimensionPixelSize(
                R.styleable.ninephoto_ninephoto_hspace, vSpace);
        t.recycle();

        addPhotoView = new View(context);
        addView(addPhotoView);
        mImageResArrayList.add(R.drawable.add_photo);
    }


    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        int childCount = this.getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = this.getChildAt(index);
            LayoutParams lParams = (LayoutParams) child.getLayoutParams();
            child.layout(lParams.left, lParams.top, lParams.left + childWidth,
                    lParams.top + childHeight);

            if (index == mImageResArrayList.size() - 1 && mImageResArrayList.size() < MAX_PHOTO_NUMBER + 2) {
                child.setBackgroundResource(R.drawable.add_photo);
                child.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        addPhotoBtnClick();
                    }
                });
            }else {
                child.setBackgroundResource(constImageIds[index]);
                child.setOnClickListener(null);
            }
        }
    }

    public void addPhotoFromLocalArray(int index) {
        if (mImageResArrayList.size() < MAX_PHOTO_NUMBER + 1) {
            View newChild = new View(getContext());
            addView(newChild);
            mImageResArrayList.add(constImageIds[index]);
            requestLayout();
            invalidate();
        }
    }

    public void addPhotoBtnClick() {
        logout("mImageResArrayList.size():"+mImageResArrayList.size());
        if (mImageResArrayList.size() == MAX_PHOTO_NUMBER + 1){
            //touch max photo limit, disable add_photo button click event
            Toast.makeText(mContext, "9 pics limit", Toast.LENGTH_SHORT).show();
        }else {
            addPhotoFromLocalArray(mImageResArrayList.size() - 1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int rw = MeasureSpec.getSize(widthMeasureSpec);
        int rh = MeasureSpec.getSize(heightMeasureSpec);

        childWidth = (rw - 2 * hSpace) / 3;
        childHeight = childWidth;

        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            //this.measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams lParams = (LayoutParams) child.getLayoutParams();
            lParams.left = (i % 3) * (childWidth + hSpace);
            lParams.top = (i / 3) * (childWidth + vSpace);
        }

        int vw = rw;
        int vh = rh;
        if (childCount < 3) {
            vw = childCount * (childWidth + hSpace);
        }
        vh = ((childCount + 3) / 3) * (childWidth + vSpace);
        setMeasuredDimension(vw, vh);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        public int left = 0;
        public int top = 0;

        public LayoutParams(Context arg0, AttributeSet arg1) {
            super(arg0, arg1);
        }

        public LayoutParams(int arg0, int arg1) {
            super(arg0, arg1);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
            super(arg0);
        }

    }

    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(
            AttributeSet attrs) {
        return new NinePhotoView.LayoutParams(getContext(), attrs);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(
            android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof NinePhotoView.LayoutParams;
    }

    private void logout(String trace){
        if (AppEnv.DEBUG){
            Log.d(TAG, trace);
        }
    }
}
