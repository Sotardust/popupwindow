package com.dai.popupwindow.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.popupwindow.R;
import com.dai.popupwindow.util.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dai on 2017/5/8.
 */

public class MultiSelectListAdapter extends BaseRecyclerAdapter<String> {

    ArrayList<String> datas;



    public HashMap<Integer, TextView> getHashMap() {
        return hashMap;
    }


    @SuppressLint("UseSparseArrays")
    HashMap<Integer, TextView> hashMap = new HashMap<>();

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position, String data) {
        System.out.println("position = " + position);
        if (viewHolder instanceof SelectHolder) {
            ((SelectHolder) viewHolder).textView.setText(data);
            hashMap.put(position, ((SelectHolder) viewHolder).textView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        System.out.println("parent = " + parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new SelectHolder(view);
    }

    @Override
    public void getData(ArrayList<String> datas) {
        this.datas = datas;
    }

    private class SelectHolder extends BaseRecyclerAdapter.ViewHolder {
        TextView textView;

        private SelectHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
