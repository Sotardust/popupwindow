package com.dai.library;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.library.util.BaseRecyclerAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dai on 2017/5/8.
 */


public class MultiSelectListAdapter extends BaseRecyclerAdapter<String> {

    private int deselectColor = Color.GRAY;
    private int selectColor = 0;
    private boolean isSelected = false;
    private Activity activity;
    private OnSelectChangeListener onSelectChangeListener;
    private Set<Integer> selectedNumber = new HashSet<>();
    private HashMap<Object, String> selectValues = new HashMap<>();

    public MultiSelectListAdapter(Activity activity) {
        this.activity = activity;
    }

    public MultiSelectListAdapter(Activity activity, int selectColor) {
        this.activity = activity;
        this.selectColor = selectColor;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position, final String data) {
        if (viewHolder instanceof SelectHolder) {
            ((SelectHolder) viewHolder).textView.setText(data);
            final boolean[] isChecked = {true};
            if (isSelected) {
                isChecked[0] = true;
                setTextColor(((SelectHolder) viewHolder).textView, selectColor);
            } else {
                setTextColor(((SelectHolder) viewHolder).textView, deselectColor);
                isChecked[0] = false;
            }

            ((SelectHolder) viewHolder).textView.setOnClickListener(new View.OnClickListener() {
                int color = deselectColor;

                @Override
                public void onClick(View v) {
                    if (!isChecked[0]) {
                        selectedNumber.add(position);
                        selectValues.put(position, data);
                        color = selectColor;
                    } else {
                        selectedNumber.remove(position);
                        selectValues.remove(position);
                        color = deselectColor;
                    }
                    isChecked[0] = !isChecked[0];
                    setTextColor(((SelectHolder) viewHolder).textView, color);
                    onSelectChangeListener.onSelectChange(selectedNumber.size(), selectValues);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (selectColor == 0) {
            selectColor = activity.getResources().getColor(R.color.colorAccent);
        }
        deselectColor = Color.GRAY;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new SelectHolder(view);
    }

    /**
     * 全选
     */
    public void selectAll() {
        for (int i = 0; i < getData().size(); i++) {
            selectedNumber.add(i);
            selectValues.put(i, getData().get(i));
        }
        setSelectColor(selectColor);
        isSelected = !isSelected;
        onSelectChangeListener.onSelectChange(selectedNumber.size(), selectValues);

    }

    /**
     * 取消全选
     */
    public void deselectAll() {
        selectedNumber.clear();
        selectValues.clear();
        setColor(deselectColor);
        isSelected = !isSelected;
        notifyDataSetChanged();
        onSelectChangeListener.onSelectChange(selectedNumber.size(), selectValues);
    }

    /**
     * 设置未选中item中text的字体颜色
     *
     * @param deselectColor int型
     */
    public void setColor(int deselectColor) {
        this.deselectColor = deselectColor;
        notifyDataSetChanged();
    }

    private void setTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }

    /**
     * 设置选中的item中text的字体颜色
     *
     * @param selectColor int型
     */
    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
        notifyDataSetChanged();
    }

    public void setOnSelectChangeListener(OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }


    public interface OnSelectChangeListener {
        void onSelectChange(int number, HashMap<Object, String> selectValues);
    }

    private class SelectHolder extends BaseRecyclerAdapter.ViewHolder {
        TextView textView;

        private SelectHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
