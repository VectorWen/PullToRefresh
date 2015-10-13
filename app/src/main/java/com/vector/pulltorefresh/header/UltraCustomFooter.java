package com.vector.pulltorefresh.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vector.pulltorefresh.R;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;

/**
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/10/13
 * Description:<p>{TODO: 用一句话描述}
 */
public class UltraCustomFooter extends FrameLayout implements LoadMoreUIHandler {

    private ImageView mImageView;
    private AnimationDrawable mAnim;

    public UltraCustomFooter(Context context) {
        super(context);
        initViews(null);
    }

    public UltraCustomFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public UltraCustomFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.ultra_custom_footer_item,this);
        mImageView = (ImageView) view.findViewById(R.id.image_view);
        mAnim = (AnimationDrawable) mImageView.getBackground();
    }

    @Override
    public void onLoading(LoadMoreContainer container) {
        mAnim.start();
    }

    @Override
    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
        if(hasMore){
            mAnim.start();
        }else{
            mAnim.stop();
        }
    }

    @Override
    public void onWaitToLoadMore(LoadMoreContainer container) {
        mAnim.stop();
    }
}
