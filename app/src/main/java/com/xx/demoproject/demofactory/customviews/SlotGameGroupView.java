package com.xx.demoproject.demofactory.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.env.AppEnv;

/**
 * Created by xx on 8/18/16.
 */
public class SlotGameGroupView extends ViewGroup {

    private final String TAG = "SlotGameGroupView";

    public final static int CHILD_VIEW_NUM = 3;

    //pic width
    private int mChildWidth = 80;
    //pic height
    private int mChildHeight = 80;

    //pic space
    private int mHSpace = 10;
    //space between slot view and btn view
    private int mVSpace = 50;

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
        mVSpace = t.getDimensionPixelSize(
                R.styleable.slotgame_slot_vspace, mVSpace);
        t.recycle();

        //add child slot view
        for (int i=0; i<CHILD_VIEW_NUM; i++){
            SingleSlotGameView child = new SingleSlotGameView(context);
            addView(child,i);
        }


        //add child btn view
        for (int j=CHILD_VIEW_NUM; j<2*CHILD_VIEW_NUM; j++){
            CustomBtnView customBtnView = new CustomBtnView(context);
            addView(customBtnView, j);
        }

        for (int k=0; k<getChildCount(); k++){
            //logout("child:"+k+" is:"+getChildAt(k).getClass());
        }
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {

        for (int i=0; i<CHILD_VIEW_NUM; i++) {
            if (getChildAt(i) instanceof SingleSlotGameView) {
                SingleSlotGameView child = (SingleSlotGameView) getChildAt(i);
                LayoutParams childParam = (LayoutParams) child.getLayoutParams();
                child.layout(childParam.left, childParam.top, childParam.left + mChildWidth, childParam.top + mChildHeight);
            }
        }
        for (int i=CHILD_VIEW_NUM; i<2*CHILD_VIEW_NUM; i++){
            if(getChildAt(i) instanceof  CustomBtnView){
                final CustomBtnView child = (CustomBtnView) getChildAt(i);
                LayoutParams childParam = (LayoutParams) child.getLayoutParams();
                child.layout(childParam.left, childParam.top, childParam.left + mChildWidth, childParam.top + mChildHeight);
                final SingleSlotGameView slotChild = (SingleSlotGameView) getChildAt(indexOfChild(child)-CHILD_VIEW_NUM);
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (slotChild.getCurStatus() == SingleSlotGameView.STATUS_RUNNING){
                            slotChild.stop();
                            child.setBtnText("STOPPING", Color.parseColor("grey"));
                            child.invalidate();
                        }else{
                            slotChild.start();
                            child.setBtnText("STOP", Color.parseColor("black"));
                            child.invalidate();
                        }
                        logout("curPicIndex:"+slotChild.getCurrentPicIndex()+" startPoine:"+slotChild.getStartPoint());
                    }
                });
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mChildWidth = (width - (CHILD_VIEW_NUM-1)*mHSpace) / CHILD_VIEW_NUM;
        mChildHeight = mChildWidth;

        int slotNum = 0;
        int btnNum = 0;
        for (int i=0; i<getChildCount(); i++){
            if (getChildAt(i) instanceof SingleSlotGameView){
                SingleSlotGameView child = (SingleSlotGameView) getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.left = slotNum * (mChildWidth + mHSpace);
                layoutParams.top = 0;
                slotNum++;
                //call children's onMeasure
                child.measure(mChildWidth, mChildHeight);
            }else if (getChildAt(i) instanceof CustomBtnView){
                CustomBtnView child = (CustomBtnView) getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.left = btnNum * (mChildWidth + mHSpace);
                layoutParams.top = mChildHeight + mVSpace;
                btnNum++;
                //call children's onMeasure
                child.measure(mChildWidth, mChildHeight*2/3);
            }
        }

        setMeasuredDimension(width, mChildHeight+mVSpace+mChildHeight*2/3);
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

    private void logout(String trace){
        if (AppEnv.DEBUG) {
            Log.d(TAG, trace);
        }
    }
}
