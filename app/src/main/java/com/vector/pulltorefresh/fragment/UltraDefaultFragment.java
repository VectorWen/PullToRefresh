package com.vector.pulltorefresh.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vector.pulltorefresh.R;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/10/12
 * Description:<p>{TODO: 用一句话描述}
 */
public class UltraDefaultFragment extends Fragment {

    private View mParent;
    private ListView mListView;
    private PtrClassicFrameLayout mPullListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mParent = inflater.inflate(R.layout.ultra_default_fragment,null,false);
        mPullListView = (PtrClassicFrameLayout) mParent.findViewById(R.id.pull_list_view);
        mListView = (ListView) mPullListView.findViewById(R.id.list_view);
        mPullListView.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPullListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullListView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        setData();
        return mParent;
    }

    private void setData() {
        ArrayList<String> data = new ArrayList<String>();
        for(int i = 0;i<20;i++){
            data.add(i + "  --  "+i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,data);
        mListView.setAdapter(adapter);
    }
}
