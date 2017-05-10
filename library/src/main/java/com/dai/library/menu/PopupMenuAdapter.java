package com.dai.library.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.library.R;
import com.dai.library.util.BaseRecyclerAdapter;

/**
 * Created by dai on 2017/5/10.
 */

public class PopupMenuAdapter extends BaseRecyclerAdapter<String> {


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, String data) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).text.setText(data);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_menu, parent, false);
        return new ViewHolder(view);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
