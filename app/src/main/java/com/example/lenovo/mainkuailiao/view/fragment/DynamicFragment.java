package com.example.lenovo.mainkuailiao.view.fragment;

import android.view.View;

import com.example.lenovo.mainkuailiao.R;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/4 14:07
 */

public class DynamicFragment extends BaseFragment{
    private View view;
    @Override
    View onCreateView() {
        view = View.inflate(getActivity(), R.layout.dynamicfragment,null);
        return view;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {

    }

}
