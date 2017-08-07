package com.example.lenovo.mainkuailiao.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/4 9:19
 */

public abstract class BaseFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onCreateView();
        initView();
        initData();
        return view;
    }
    //初始化视图抽象方法
    abstract View onCreateView();
    //初始化控件抽象方法
    abstract void initView();
    //初始化数据抽象方法
    abstract void initData();
}
