package com.vector.pulltorefresh;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ultra_pull_activity_btn:
                startActivity(UltraPullRefreshActivity.class);
                break;

        }
    }
}
