package com.example.lenovo.mainkuailiao.presenter;

import android.content.Context;

import com.example.lenovo.greendao.gen.DaoMaster;
import com.example.lenovo.greendao.gen.DaoSession;
import com.example.lenovo.greendao.gen.MassagedbDataDao;
import com.example.lenovo.mainkuailiao.modle.bean.MassagedbData;
import com.example.lenovo.mainkuailiao.modle.bean.MassgerListData;
import com.example.lenovo.mainkuailiao.modle.net.NetDataCallback;
import com.example.lenovo.mainkuailiao.modle.net.OkHttpUtils;
import com.example.lenovo.mainkuailiao.utils.ToastUtils;
import com.example.lenovo.mainkuailiao.view.iview.IMainView;
import com.example.lenovo.mainkuailiao.view.iview.MainWiFi;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 16:35
 */

public class MassagerPresenter<T>{
    private DaoMaster.DevOpenHelper devOpenHelper;
    private MassagedbDataDao massagedbDataDao;
    private final OkHttpUtils httpUtils;
    private List<MassagedbData> mass;
    private IMainView miMainView;
    private DaoSession daoSession;
    private MainWiFi mainWiFi;
    private Context context;

    public MassagerPresenter(IMainView iMainView,MainWiFi mainWiFi,Context context) {
        this.miMainView = iMainView;
        this.context = context;
        this.mainWiFi = mainWiFi;
        httpUtils = new OkHttpUtils();
    }

    public void loadDataFromServer(String url, Class<T> t) {
        httpUtils.initData(url, new NetDataCallback<T>() {

            @Override
            public void callback(T o) {
                miMainView.successCallback(o);
            }

            @Override
            public void err(int errCode, String errMsg) {
                miMainView.errCallback(errMsg, 500);
            }
        }, t);
    }
    //创建数据库
    public void getAddMassageDb() {
        devOpenHelper = new DaoMaster.DevOpenHelper(context,"massage.db");
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
        massagedbDataDao = daoSession.getMassagedbDataDao();
        mass = new ArrayList<>();
    }
    //网络请求成功时调用添加数据库
    public void getAddList(List<MassgerListData.DataBean> list2) {
        if(list2!=null&&list2.size()>0) {
            for (MassgerListData.DataBean iter : list2) {
                MassagedbData massagedbData = new MassagedbData();
                massagedbData.setImageurl(iter.getUserImg());
                massagedbData.setUsername(iter.getUserName());
                mass.add(massagedbData);
            }
            getMassagelist(mass);
        }
    }
    //添加数据库时调用
    public void getMassagelist(List<MassagedbData> massagelist) {
        if (massagelist != null && massagelist.size() > 0) {
            for (MassagedbData massagedbData : massagelist) {
                long insert = massagedbDataDao.insert(massagedbData);
                System.out.println("massagedbData"+"----------"+insert);
                ToastUtils.getToas(context, "添加数据库成功");
                System.out.println("massagedbData = " + massagedbDataDao.queryBuilder().list().size());
            }
        } else {
            ToastUtils.getToas(context, "添加数据库失败");
        }
    }

    //查询数据库时调用
    public void getListMassageDb() {
        List<MassagedbData> list = massagedbDataDao.queryBuilder().list();
        System.out.println("massagedbData = " + list.size());
        if (list!=null&&list.size()>0){
            ToastUtils.getToas(context,"数据库数据");
            mainWiFi.onaddListdb(list);
        }else{
            ToastUtils.getToas(context,"数据库暂无数据");
        }
    }

    //判断是否有网络
    public void getWifi(boolean wifi) {
        if (wifi==true) {
            ToastUtils.getToas(context, "网络可用");
            mainWiFi.onTrueNet(wifi);
        } else {
            ToastUtils.getToas(context, "网络不可用");
            mainWiFi.onFailesdb(wifi);
        }
    }
}
