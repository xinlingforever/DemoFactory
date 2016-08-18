package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xx.demoproject.demofactory.R;

/**
 * Created by xx on 8/18/16.
 */
public class SlotGameGroupView extends ViewGroup {

    private final int CHILD_VIEW_NUM = 3;

    //pic width
    private int mChildWidth = 80;
    //pic height
    private int mChildHeight = 80;

    //pic space
    private int mHSpace = 10;


    public SlotGameGroupView(Context context) {
        super(context);
    }

    public SlotGameGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlotGameGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.slotgame, 0, 0);
        mHSpace = t.getDimensionPixelSize(
                R.styleable.slotgame_slot_hspace, mHSpace);
        t.recycle();

        //add child view
        for (int i=0; i<CHILD_VIEW_NUM; i++){
            SingleSlotGameView child = new SingleSlotGameView(context);
            addView(child);
        }
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        for (int i=0; i<CHILD_VIEW_NUM; i++){
            SingleSlotGameView child = (SingleSlotGameView) getChildAt(i);
            LayoutParams childParam = (LayoutParams) child.getLayoutParams();
            child.layout(childParam.left, childParam.top, childParam.left + mChildWidth, childParam.top + mChildHeight);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mChildWidth = (width - (CHILD_VIEW_NUM-1)*mHSpace) / CHILD_VIEW_NUM;
        mChildHeight = mChildWidth;

        for (int i=0; i<getChildCount(); i++){
            SingleSlotGameView child = (SingleSlotGameView) getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            layoutParams.left = i * (mChildWidth + mHSpace);
            layoutParams.top = 0;
            child.measure(mChildWidth, mChildHeight);
        }

        setMeasuredDimension(width, mChildHeight);
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
        return new SlotGameGroupView.LayoutParams(getContext(), attrs);
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
        return p instanceof SlotGameGroupView.LayoutParams;
    }
}
