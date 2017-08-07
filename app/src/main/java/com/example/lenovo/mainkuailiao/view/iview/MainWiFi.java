package com.example.lenovo.mainkuailiao.view.iview;

import com.example.lenovo.mainkuailiao.modle.bean.MassagedbData;

import java.util.List;

/**
 * 类描述：网络判断回调接口
 * 创建人：lenovo
 * 创建时间：2017/8/6 10:50
 */

public interface MainWiFi {
    void onTrueNet(boolean wifi);
    void onFailesdb(boolean wifi);
    void onaddListdb(List<MassagedbData> massagedbDatas);
}
