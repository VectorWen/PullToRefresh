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
import com.vector.pulltorefresh.header.UltraCustomFooter;
import com.vector.pulltorefresh.header.UltraCustomHeader;

import java.util.ArrayList;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
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
public class UltraCustomFooterFragment extends Fragment {

    private View mParent;
    private ListView mListView;
    private PtrClassicFrameLayout mPullListView;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private ArrayList<String> mData;
    private ArrayAdapter<String> mAdapter;
    private UltraCustomFooter mFooter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mParent = inflater.inflate(R.layout.ultra_load_more_fragment,null,false);
        mPullListView = (PtrClassicFrameLayout) mParent.findViewById(R.id.pull_list_view);
        UltraCustomHeader header = new UltraCustomHeader(getActivity());
        mPullListView.setHeaderView(header);
        mPullListView.addPtrUIHandler(header);
        mListView = (ListView) mPullListView.findViewById(R.id.list_view);

        //下拉刷新header
        mPullListView.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mFooter.setVisibility(View.GONE);
                mPullListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.clear();
                        for(int i = 0;i<20;i++){
                            mData.add(i + "  --  " + i);
                        }
                        mAdapter.notifyDataSetInvalidated();
                        mPullListView.refreshComplete();
                        loadMoreListViewContainer.loadMoreFinish(true,true);
                    }
                }, 10000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }
        });


        //加载更多头
       loadMoreListViewContainer = (LoadMoreListViewContainer) mParent.findViewById(R.id.load_more_list_view_container);
//        loadMoreListViewContainer.useDefaultHeader();
        mFooter = new UltraCustomFooter(getActivity());
        loadMoreListViewContainer.setLoadMoreView(mFooter);
        loadMoreListViewContainer.setLoadMoreUIHandler(mFooter);
        // binding view and data
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(final LoadMoreContainer loadMoreContainer) {
                if(mPullListView.isRefreshing()){ //头部刷新中...
                    return;
                }
                mFooter.setVisibility(View.VISIBLE);
                mPullListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int size = mData.size();
                        for(int i = size;i<size+20;i++){
                            mData.add(i + "  --  " + i);
                        }
                        mAdapter.notifyDataSetChanged();

                        if(mData.size() > 50){
                            loadMoreContainer.loadMoreFinish(false,false);
                        }else{
                            loadMoreContainer.loadMoreFinish(true,true);
                        }
                    }
                }, 10000);
            }
        });

        setData();
        return mParent;
    }

    private void setData() {
        mData = new ArrayList<String>();
        for(int i = 0;i<20;i++){
            mData.add(i + "  --  " + i);
        }

        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,mData);
        mListView.setAdapter(mAdapter);
    }
}
