package com.example.lenovo.mainkuailiao.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.mainkuailiao.modle.bean.DrawData;
import com.example.lenovo.mainkuailiao.utils.ToastUtils;
import com.example.lenovo.mainkuailiao.view.adapter.TowRecycAdapter;
import com.example.lenovo.mainkuailiao.view.iview.towview.TowView;
import java.util.List;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 10:22
 */

public class Towpresenter implements TowView{
    private Context context;
    private List<DrawData> list;
    public Towpresenter(Context context,List<DrawData> list) {
        this.context = context;
        this.list = list;
    }
    public void onRecycler(RecyclerView recyclerView){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        TowRecycAdapter towRecycAdapter = new TowRecycAdapter(context,list);
        recyclerView.setAdapter(towRecycAdapter);
    }

    @Override
    public void OnLink(View view, int pos) {
        ToastUtils.getToas(context,list.get(pos).getDraname());
    }
    public void getStopTowPresent(){
        if (context != null){
            context = null;
        }
    }
}
