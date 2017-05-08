package com.dai.popupwindow.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.popupwindow.R;
import com.dai.popupwindow.util.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dai on 2017/5/8.
 */

public class MultiSelectListAdapter extends BaseRecyclerAdapter<String> {

    ArrayList<String> datas;

    private Set<Integer> selectedNumber = new HashSet<>();

    public void setOnSelectChangeListener(OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    private OnSelectChangeListener onSelectChangeListener;

    public interface OnSelectChangeListener {
        void onSelectChange(int number);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position, String data) {
        System.out.println("position = " + position);
        if (viewHolder instanceof SelectHolder) {
            ((SelectHolder) viewHolder).textView.setText(data);
            ((SelectHolder) viewHolder).textView.setOnClickListener(new View.OnClickListener() {
                boolean isChecked = true;
                @Override
                public void onClick(View v) {
                    if (isChecked) {
                        selectedNumber.add(position);
                        ((SelectHolder) viewHolder).textView.setTextColor(Color.rgb(45, 234, 23));
                    } else {
                        selectedNumber.remove(position);
                        ((SelectHolder) viewHolder).textView.setTextColor(Color.BLUE);
                    }
                    isChecked = !isChecked;
                    onSelectChangeListener.onSelectChange(selectedNumber.size());
                }
            });

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        System.out.println("parent = " + parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new SelectHolder(view);
    }

    public void setSelectAll() {
        for (int i = 0; i < datas.size(); i++) {
            selectedNumber.add(i);
        }
    }

    public void setCancelSelectAll() {
        selectedNumber.clear();
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
