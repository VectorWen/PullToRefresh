package com.vector.pulltorefresh;

import android.app.Activity;
import android.content.Intent;

/**
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/10/12
 * Description:<p>{TODO: 用一句话描述}
 */
public class BaseActivity extends Activity {

    public void showFragment(String fragmentName){
        Intent intent = new Intent(this,ShowFragmentActivity.class);
        intent.putExtra(ShowFragmentActivity.FRAGMENT_NAME,fragmentName);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls){
        startActivity(new Intent(this,cls));
    }

}
