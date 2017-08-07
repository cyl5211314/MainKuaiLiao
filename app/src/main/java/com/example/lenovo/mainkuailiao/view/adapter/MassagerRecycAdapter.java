package com.example.lenovo.mainkuailiao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.mainkuailiao.R;
import com.example.lenovo.mainkuailiao.modle.bean.MassgerListData;
import com.example.lenovo.mainkuailiao.modle.net.GlideCircleTransform;
import com.example.lenovo.mainkuailiao.view.iview.towview.TowView;

import java.util.List;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 10:28
 */

public class MassagerRecycAdapter extends RecyclerView.Adapter<MassagerRecycAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<MassgerListData.DataBean> list;
    private TowView towView;

    public void setTowView(TowView towView) {
        this.towView = towView;
    }

    public MassagerRecycAdapter(Context context, List<MassgerListData.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.towactivadapter,null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUserImg()).into(holder.homeimage);
//        Glide.with(context).load(list.get(position).getUserImg()).bitmapTransform(new CropCircleTransformation(this))
//                .crossFade(1000).into(image2);
        Glide .with(context)
                .load(list.get(position).getUserImg())
                //设置圆形图片
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(holder.homeimage);
        holder.towadaptername.setText(list.get(position).getUserName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if(view!=null){
            towView.OnLink(view, (Integer) view.getTag());
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView homeimage;
        private TextView towadaptername;

        public ViewHolder(View itemView) {
            super(itemView);
            homeimage = itemView.findViewById(R.id.towadapter_image);
            towadaptername = itemView.findViewById(R.id.towadapter_name);
        }
    }
}
