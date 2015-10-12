package com.vector.pulltorefresh;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vector.pulltorefresh.fragment.UltraAutoDefaultFragment;
import com.vector.pulltorefresh.fragment.UltraDefaultFragment;
import com.vector.pulltorefresh.fragment.UltraLoadMoreFragment;

/**
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/10/12
 * Description:<p>{TODO: 用一句话描述}
 */
public class UltraPullRefreshActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ultra_pull_refresh_activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.default_pull_btn:
                showFragment(UltraDefaultFragment.class.getName());
                break;
            case R.id.default_auto_pull_btn:
                showFragment(UltraAutoDefaultFragment.class.getName());
                break;
            case R.id.load_more_btn:
                showFragment(UltraLoadMoreFragment.class.getName());
                break;
        }
    }


}
