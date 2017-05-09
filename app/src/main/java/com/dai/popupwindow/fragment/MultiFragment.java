package com.dai.popupwindow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dai.library.MultiSelectPopWindow;
import com.dai.popupwindow.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dai on 2017/5/5.
 */

public class MultiFragment extends Fragment {

    private ArrayList<String> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutil, container, false);
        Button button = (Button) view.findViewById(R.id.button1);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiSelectPopWindow popWindow = new MultiSelectPopWindow.Builder(getActivity())
                        .setTitle("标题")
                        .setCancelText("取消")
                        .setConfirmText("确认")
                        .setData(data)
                        .setOnCancelListener(new MultiSelectPopWindow.Builder.OnCancelClickListener() {
                            @Override
                            public void onCancelClick(PopupWindow popup) {
                                popup.dismiss();
                            }
                        })
                        .setOnConfirmListener(new MultiSelectPopWindow.Builder.OnConfirmClickListener() {
                            @Override
                            public void onConfirmClick(PopupWindow popup, HashMap<Object, String> data) {
                                popup.dismiss();
                            }
                        })
                        .build();
                popWindow.show(getView());
            }
        });
        return view;
    }

    private void initData() {
        data.add("底部弹出多选框");
        data.add("某控件下弹窗");
        data.add("屏幕中间弹窗");
        data.add("底部弹出多选框1");
        data.add("某控件下弹窗1");
        data.add("屏幕中间弹窗1");
        data.add("底部弹出多选框2");
        data.add("某控件下弹窗2");
        data.add("屏幕中间弹窗2");
    }
}
