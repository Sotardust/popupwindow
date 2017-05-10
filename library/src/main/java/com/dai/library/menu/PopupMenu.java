package com.dai.library.menu;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.dai.library.R;

/**
 * Created by dai on 2017/5/10.
 */

public class PopupMenu {

    private PopupWindow popupWindow;
    private Builder builder;

    public static class Builder {

        private View view;
        private boolean monospaced;
        private Activity activity;
        private int background;
        private int width;
        private int height;
        private RecyclerView.Adapter adapter;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public PopupMenu build() {
            return new PopupMenu(this);
        }

        public Builder setBackground(@DrawableRes int background) {
            this.background = background;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        /**
         * 与父视图等宽
         *
         * @param monospaced boolean型
         * @return Builder
         */
        public Builder setMonospaced(boolean monospaced) {
            this.monospaced = monospaced;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }

    private PopupMenu(Builder builder) {
        this.builder = builder;
        View popView = View.inflate(builder.activity, R.layout.popu_menu, null);
        RecyclerView recyclerView = (RecyclerView) popView.findViewById(R.id.popup_menu_recyclerView);
        int width = WindowManager.LayoutParams.WRAP_CONTENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        if (builder.monospaced) {
            width = builder.view.getWidth();
        }
        if (builder.width != 0) {
            width = builder.width;
        }
        if (builder.height != 0) {
            height = builder.height;
        }
        popupWindow = new PopupWindow(popView, width, height);
        if (builder.background != 0) {
            popView.setBackgroundResource(builder.background);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(builder.activity.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        if (builder.adapter != null) {
            recyclerView.setAdapter(builder.adapter);
        }
    }


    public void show() {
        if (popupWindow != null) {
            popupWindow.showAsDropDown(builder.view);
        }
    }

    /**
     * @param xoff x轴方向的偏移量
     * @param yoff y轴方向的偏移量
     */
    public void show(int xoff, int yoff) {
        if (popupWindow != null && builder.view != null) {
            popupWindow.showAsDropDown(builder.view, xoff, yoff);
        }
    }

    public void showTop() {
        if (popupWindow != null) {
            popupWindow.setAnimationStyle(R.style.pop_menu);
            popupWindow.showAtLocation(builder.view, Gravity.TOP, 0, 0);
            backgroundAlpha(0.5f);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        try {
            WindowManager.LayoutParams lp = builder.activity.getWindow().getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0
            builder.activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
