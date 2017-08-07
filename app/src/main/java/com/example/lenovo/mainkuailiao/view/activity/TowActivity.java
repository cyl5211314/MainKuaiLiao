package com.example.lenovo.mainkuailiao.view.activity;

import android.support.v7.widget.RecyclerView;

import com.example.lenovo.mainkuailiao.R;
import com.example.lenovo.mainkuailiao.modle.bean.DrawData;
import com.example.lenovo.mainkuailiao.presenter.Towpresenter;

import java.util.ArrayList;
import java.util.List;

public class TowActivity extends BaseActivity{
    private RecyclerView towrecyc;
    private Towpresenter towpresenter;
    private List<DrawData> list;

    //初始化Activity加载布局
    @Override
    int ContentView() {
        return R.layout.activity_tow;
    }
    //初始化控件
    @Override
    void initView() {

    }
    //初始化数据
    @Override
    void initData() {
        list = new ArrayList<>();

        towpresenter.onRecycler(towrecyc);
    }
    //初四化list集合数据
    private void initList() {
        list.add(new DrawData(R.drawable.u0,"了解会员特权"));
        list.add(new DrawData(R.drawable.u0,"JPPAI钱包"));
        list.add(new DrawData(R.drawable.u0,"个性装扮"));
        list.add(new DrawData(R.drawable.u0,"我的收藏夹"));
        list.add(new DrawData(R.drawable.u0,"我的相册"));
        list.add(new DrawData(R.drawable.u0,"我的文件"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        towpresenter.getStopTowPresent();
    }

}



