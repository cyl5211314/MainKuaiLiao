package com.example.lenovo.mainkuailiao.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.mainkuailiao.R;
import com.example.lenovo.mainkuailiao.modle.bean.MassagedbData;
import com.example.lenovo.mainkuailiao.modle.bean.MassgerListData;
import com.example.lenovo.mainkuailiao.presenter.MassagerPresenter;
import com.example.lenovo.mainkuailiao.utils.ToastUtils;
import com.example.lenovo.mainkuailiao.utils.WiFiUtils;
import com.example.lenovo.mainkuailiao.view.adapter.MassagerRecycAdapter;
import com.example.lenovo.mainkuailiao.view.adapter.MassagerRecycAdapter2;
import com.example.lenovo.mainkuailiao.view.iview.IMainView;
import com.example.lenovo.mainkuailiao.view.iview.MainWiFi;
import com.example.lenovo.mainkuailiao.view.iview.towview.TowView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/4 9:16
 */

public class MassageFragment extends BaseFragment implements IMainView<MassgerListData>, MainWiFi {
    private static final String URL = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page=1";
    private View view;
    private RecyclerView massagerRecycler;
    private List<MassgerListData.DataBean> list;
    private List<MassagedbData> massagelist;
    private MassagerPresenter massagerPresenter;
    private MassagerRecycAdapter massagerRecycAdapter;
    private MassagerRecycAdapter2 massagerRecycAdapter2;

    //查找布局
    @Override
    View onCreateView() {
        view = View.inflate(getActivity(), R.layout.massagefragment, null);
        return view;
    }

    //初始化数据控件
    @Override
    void initView() {
        list = new ArrayList<>();
        massagelist = new ArrayList<>();
        massagerRecycler = view.findViewById(R.id.massage_recycview);
        //创建p层对象调用有参构造方法传要用的数据
        massagerPresenter = new MassagerPresenter(this, this, getActivity());
        //调用访问数据库的方法数据库
        massagerPresenter.getAddMassageDb();
    }

    //判断网络是否可用
    @Override
    void initData() {
        massagerPresenter.getWifi(WiFiUtils.isNetworkAvailable(getActivity()));
    }

    //有网络回调
    @Override
    public void onTrueNet(boolean wifi) {
        //有网络适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        massagerRecycler.setLayoutManager(manager);
        massagerRecycAdapter = new MassagerRecycAdapter(getActivity(), list);
        massagerRecycler.setAdapter(massagerRecycAdapter);
        massagerRecycAdapter.setTowView(new TowView() {
            @Override
            public void OnLink(View view, int pos) {
                ToastUtils.getToas(getActivity(), list.get(pos).getUserName());
            }
        });
        //请求网络
        massagerPresenter.loadDataFromServer(URL, MassgerListData.class);
    }

    //无网络回调
    @Override
    public void onFailesdb(boolean wifi) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        massagerRecycler.setLayoutManager(manager);
        massagerRecycAdapter2 = new MassagerRecycAdapter2(getActivity(), massagelist);
        massagerRecycler.setAdapter(massagerRecycAdapter2);
        massagerRecycAdapter2.setTowView(new TowView() {
            @Override
            public void OnLink(View view, int pos) {
                ToastUtils.getToas(getActivity(), massagelist.get(pos).getUsername());
            }
        });
        massagerPresenter.getListMassageDb();
    }

    //查询数据库回调方法
    @Override
    public void onaddListdb(List<MassagedbData> massagedbDatas) {
        massagelist.addAll(massagedbDatas);
        massagerRecycAdapter2.notifyDataSetChanged();
    }

    //网络请求成功回调
    @Override
    public void successCallback(MassgerListData massgerListData) {
        //添加有网络请求数据的集合
        list.addAll(massgerListData.getData());
        massagerRecycAdapter.notifyDataSetChanged();
        massagerPresenter.getAddList(list);
    }

    //网络请求失败回调
    @Override
    public void errCallback(String msg, int code) {

    }
}
