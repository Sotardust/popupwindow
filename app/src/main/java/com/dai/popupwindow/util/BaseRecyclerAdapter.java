package com.dai.popupwindow.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dai on 2017/5/5.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> datas;

    private OnItemClickLister<T> onItemClickLister;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final T value = datas.get(position);
        onBindViewHolder(holder, position, value);
        if (onItemClickLister != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLister.onItemClick(position, value);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setOnItemClickLister(OnItemClickLister<T> onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public ArrayList<T> getDatas() {
      return  datas ;
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, T data);

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    public interface OnItemClickLister<T> {
        void onItemClick(int position, T data);
    }

}
