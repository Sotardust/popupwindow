package com.dai.popupwindow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.popupwindow.util.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by dai on 2017/5/5.
 */

public class MyAdapter extends BaseRecyclerAdapter<String> {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, String data) {
        System.out.println("position = " + position);
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).textView.setText(data);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        System.out.println("parent = " + parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void getData(ArrayList<String> datas) {
    }

    private class MyHolder extends BaseRecyclerAdapter.ViewHolder {
        TextView textView;

        private MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
