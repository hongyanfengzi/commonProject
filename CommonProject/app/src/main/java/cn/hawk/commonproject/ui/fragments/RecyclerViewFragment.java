package cn.hawk.commonproject.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import cn.hawk.commonlib.base.BaseFragment;
import cn.hawk.commonlib.base.MVPFragment;
import cn.hawk.commonlib.interfaces.OnItemClickListener;
import cn.hawk.commonlib.utils.DividerGridItemDecoration;
import cn.hawk.commonlib.widgets.StarMenu;
import cn.hawk.commonproject.AppContext;
import cn.hawk.commonproject.R;
import cn.hawk.commonproject.adapters.SimpleStringArrayAdapter;
import cn.hawk.commonproject.adapters.StaggeredHomeAdapter;
import cn.hawk.commonproject.contracts.RecyclerViewContract;
import cn.hawk.commonproject.presents.RecyclerViewPresenter;

/**
 * Created by kehaowei on 2017/3/24.
 */

public class RecyclerViewFragment extends MVPFragment<RecyclerViewPresenter> implements RecyclerViewContract.View, OnItemClickListener {

    private RecyclerView mRecyclerView;
    private StaggeredHomeAdapter mAdapter;

    private StarMenu starMenu;

    public static RecyclerViewFragment newInstance() {
        Bundle args = new Bundle();
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindView() {
        super.bindView();
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.rv_list);
        starMenu = (StarMenu) getView().findViewById(R.id.star_menu);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        if (null != starMenu) {
            starMenu.setMenuItemClickListener(new StarMenu.OnMenuItemClickListener() {
                @Override
                public void onClick(View view, int pos) {
                    AppContext.getInstance().logi("Hawk", "view : " + view + " on clicked in position : " + pos);
                }
            });
        }
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void refreshList(ArrayList<String> infos) {
        if (null == mAdapter) {
            /*mAdapter = new SimpleStringArrayAdapter(getActivity(), infos);*/
            mAdapter = new StaggeredHomeAdapter(getActivity(), infos);
            mAdapter.setmListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.refresh(infos);
        }
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            /*mAdapter = new SimpleStringArrayAdapter(getActivity(), new ArrayList<String>());*/
            mAdapter = new StaggeredHomeAdapter(getActivity(), new ArrayList<String>());
            mAdapter.setmListener(this);
            /*mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
            /*mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));*/
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mAdapter);
            /*mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));*/
            /*mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));*/
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        mAdapter.addData(position, "点击增加 ：" + position);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        mAdapter.removeData(position);
    }

    @Override
    public RecyclerViewPresenter createPresenter() {
        return new RecyclerViewPresenter(getActivity(), this);
    }
}
