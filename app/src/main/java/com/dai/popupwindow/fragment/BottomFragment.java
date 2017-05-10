package com.dai.popupwindow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dai.library.menu.PopupMenu;
import com.dai.popupwindow.R;
import com.dai.popupwindow.util.PopupMenuAdapter;

import java.util.ArrayList;

/**
 * Created by dai on 2017/5/5.
 */

public class BottomFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        Button button = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        Button button4 = (Button) view.findViewById(R.id.button4);
        Button button5 = (Button) view.findViewById(R.id.button5);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
//        PopupMenu popupMenu = new PopupMenu.Builder(getActivity())
//                .setAdapter(adapter)
//                .setBackground(R.color.colorPrimary)
//                .setMonospaced(true)
//                .setWidth(500)
//                .setView(view)
//                .build();
        return view;
    }

    @Override
    public void onClick(View view) {
        PopupMenuAdapter adapter = new PopupMenuAdapter();
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add("菜单" + i);
        }
        adapter.setData(data);
        switch (view.getId()) {
            case R.id.button1:
                getPopupMenu()
                        .setAdapter(adapter)
                        .setView(view)
                        .setWidth(500)
                        .build()
                        .show(); ;
                break;
            case R.id.button2:
                getPopupMenu()
                        .setAdapter(adapter)
                        .setView(view)
                        .setMonospaced(true)
                        .build()
                        .show();
                break;
            case R.id.button3:
                getPopupMenu()
                        .setAdapter(adapter)
                        .setView(view)
                        .setBackground(R.color.colorPrimary)
                        .build()
                        .show();
                break;
            case R.id.button4:
                getPopupMenu()
                        .setAdapter(adapter)
                        .setView(view)
                        .setBackground(R.color.colorPrimary)
                        .build()
                        .show(30,30);
                break;
            case R.id.button5:
                getPopupMenu()
                        .setAdapter(adapter)
                        .setView(getView())
                        .setWidth(500)
                        .build()
                        .showTop();
                break;
        }
    }

    public PopupMenu.Builder getPopupMenu() {
        return new PopupMenu.Builder(getActivity());
    }
}
