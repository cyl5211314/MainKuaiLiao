package com.example.lenovo.mainkuailiao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.mainkuailiao.R;
import com.example.lenovo.mainkuailiao.modle.bean.DrawData;
import com.example.lenovo.mainkuailiao.view.iview.towview.TowView;

import java.util.List;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 10:28
 */

public class TowRecycAdapter extends RecyclerView.Adapter<TowRecycAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<DrawData> list;
    private TowView towView;

    public TowRecycAdapter(TowView towView) {
        this.towView = towView;
    }

    public TowRecycAdapter(Context context, List<DrawData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.towadapter,null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.towimage);
        holder.towtxt.setText(list.get(position).getDraname());
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
        private ImageView towimage;
        private TextView towtxt;

        public ViewHolder(View itemView) {
            super(itemView);
            towimage = itemView.findViewById(R.id.tow_adapter_image);
            towtxt = itemView.findViewById(R.id.tow_adapter_text);
        }
    }
}
