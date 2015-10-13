package com.vector.pulltorefresh.header;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vector.pulltorefresh.R;
import com.vector.pulltorefresh.logger.Logger;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/10/13
 * Description:<p>{TODO: 用一句话描述}
 */
public class UltraCustomHeader extends FrameLayout implements PtrUIHandler {

    private Logger logger = Logger.getLogger();

    private ImageView mImageView;
    private AnimationDrawable mAnim; //跑步动画
    private ObjectAnimator mObjectAnim;//移动动画
    private int mInitX;//初始化的位置
    private int mMove;

    public UltraCustomHeader(Context context) {
        super(context);
        initViews(null);
    }

    public UltraCustomHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public UltraCustomHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.ultra_custom_header_item, this);
        mImageView = (ImageView) header.findViewById(R.id.image_view);
        mAnim = (AnimationDrawable) mImageView.getDrawable();
        mMove = mInitX+ getContext().getResources().getDimensionPixelSize(R.dimen.pull_anim);
        mObjectAnim = ObjectAnimator.ofFloat(mImageView,"translationX",mInitX,mMove/2,mInitX,-mMove/2,mInitX).setDuration(2200);
        mObjectAnim.setInterpolator(new LinearInterpolator());
        mObjectAnim.setRepeatCount(100000);
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        logger.d("onUIReset()");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        logger.d("onUIRefreshPrepare()");
        mImageView.setTranslationX(mInitX);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        logger.d("onUIRefreshBegin()");
        mImageView.setVisibility(VISIBLE);
        mAnim.start();
        mObjectAnim.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        logger.d("onUIRefreshComplete()");
        mAnim.stop();
        mObjectAnim.cancel();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean b, byte b1, PtrIndicator ptrIndicator) {

        if(!b) {
            return;
        }

        if(b1 != PtrFrameLayout.PTR_STATUS_PREPARE){
            return;
        }

        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if(currentPos > getHeight()){
        }else{
            ViewGroup.LayoutParams params = mImageView.getLayoutParams();
            params.height = currentPos;
            params.width = currentPos;
            mImageView.setLayoutParams(params);
        }
        logger.d("onUIPositionChange() currentPos = %d , lastPos = %d",currentPos,lastPos);
    }
}
