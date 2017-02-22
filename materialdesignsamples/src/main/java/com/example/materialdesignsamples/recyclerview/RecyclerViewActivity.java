package com.example.materialdesignsamples.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.materialdesignsamples.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zf on 17-2-21.
 * 下拉刷新，上拉加载
 */
public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.scroll5_bt)
    Button scroll5Bt;
    @BindView(R.id.scrollToBottom_bt)
    Button scrollToBottomBt;
    @BindView(R.id.recyclerview)
    RecyclerView mRv;
    @BindView(R.id.scrollToTop_bt)
    Button scrollToTopBt;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<String> mData;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager llm;
    private boolean isScroll=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recyclerview);
        ButterKnife.bind(this);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mSwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 通过 setEnabled(false) 禁用下拉刷新
//        mSwipeRefreshLayout.setEnabled(true);

        // 设定下拉圆圈的背景
//        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.red);

        //设置手势下拉刷新的监听
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        llm = new LinearLayoutManager(this);
        mRv.setLayoutManager(llm);
        mAdapter = new RecyclerViewAdapter(this, R.layout.recyclerview_item, initData());
        mRv.setAdapter(mAdapter);

        //监听滑动状态
        mRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            Boolean isScrolling = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isScroll) {
                    int lastVisibleItem = llm.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = llm.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        loadMore();
                        isScroll = false;
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isScrolling = true;
                } else {
                    isScrolling = false;
                }
            }
        });
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        return intent;
    }

    public List<String> initData() {
        mData = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            mData.add(String.valueOf(i));
        }
        return mData;
    }

    public void loadMore(){
        onPreLoadMore();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.add("加载第1条数据");
                mData.add("加载第2条数据");
                mData.add("加载第3条数据");
                onPostLoadMore();
            }
        }, 3000);
    }

    public void onPreLoadMore() {
        mData.add("last");
        mAdapter.notifyDataSetChanged();
    }

    public void onPostLoadMore() {
        mData.remove("last");
        isScroll=true;
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.scroll5_bt, R.id.scrollToBottom_bt, R.id.scrollToTop_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            //滑动到第５项
            case R.id.scroll5_bt:
                llm.scrollToPosition(4);
                break;
            //滑动到底部
            case R.id.scrollToBottom_bt:
                scrollToBottom(llm, mData);
                break;
            //滑动到顶部
            case R.id.scrollToTop_bt:
                scrollToTop(llm);
                break;
        }
    }

    public void scrollToBottom(RecyclerView.LayoutManager lm, List data) {
        if (null == data || data.size() == 0) {
            return;
        }

        lm.scrollToPosition(data.size() - 1);
    }

    public void scrollToTop(RecyclerView.LayoutManager lm) {
        lm.scrollToPosition(0);
    }

    @Override
    public void onRefresh() {
        // 刷新动画开始后回调到此方法
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RecyclerViewActivity.this, "刷新界面", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
