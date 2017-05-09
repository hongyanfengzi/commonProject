package cn.hawk.commonproject.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.hawk.commonlib.base.BaseFragment;
import cn.hawk.commonlib.base.MVPFragment;
import cn.hawk.commonproject.R;
import cn.hawk.commonproject.contracts.MainContract;
import cn.hawk.commonproject.presents.MainPresnter;
import cn.hawk.commonproject.ui.activities.CardDisplayActivity;
import cn.hawk.commonproject.ui.activities.CoverFlowActivity;
import cn.hawk.commonproject.ui.activities.ImageHandleActivity;
import cn.hawk.commonproject.ui.activities.RecyclerViewActivity;

/**
 * Created by kehaowei on 2017/2/22.
 */

public class MainFragment extends MVPFragment<MainPresnter> implements MainContract.View, View.OnClickListener {
    private TextView tvOutPut;
    private Button btnGoImage;
    private Button btnGoRecycler;
    private Button btnGoCardDisplay;
    private Button btnGoCoverFlow;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void bindView() {
        super.bindView();
        tvOutPut = (TextView) getView().findViewById(R.id.output);
        btnGoImage = (Button) getView().findViewById(R.id.go_image);
        btnGoRecycler = (Button) getView().findViewById(R.id.go_recycler_view);
        btnGoCardDisplay = (Button) getView().findViewById(R.id.go_card_display);
        btnGoCoverFlow = (Button) getView().findViewById(R.id.go_cover_flow);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btnGoImage.setOnClickListener(this);
        btnGoRecycler.setOnClickListener(this);
        btnGoCardDisplay.setOnClickListener(this);
        btnGoCoverFlow.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showOutput(String output) {
        if (TextUtils.isEmpty(output))
            return;
        tvOutPut.setText(output);
    }

    private void goImageHandle() {
        startActivity(new Intent(getActivity(), ImageHandleActivity.class));
    }

    private void goRecyclerView() {
        startActivity(new Intent(getActivity(), RecyclerViewActivity.class));
    }

    private void goCardDisplay() {
        startActivity(new Intent(getActivity(), CardDisplayActivity.class));
    }

    private void goCoverFlow() {
        startActivity(new Intent(getActivity(), CoverFlowActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_image:
                goImageHandle();
                break;
            case R.id.go_recycler_view:
                goRecyclerView();
                break;
            case R.id.go_card_display:
                goCardDisplay();
                break;
            case R.id.go_cover_flow:
                goCoverFlow();
                break;
        }
    }

    @Override
    public MainPresnter createPresenter() {
        return new MainPresnter(this);
    }
}
