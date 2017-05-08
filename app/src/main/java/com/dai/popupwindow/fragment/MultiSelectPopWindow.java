package com.dai.popupwindow.fragment;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dai.popupwindow.R;

import java.util.ArrayList;

/**
 * Created by dai on 2017/5/5.
 */

public class MultiSelectPopWindow {

    private PopupWindow popupWindow;
    private MultiSelectListAdapter adapter;
    private TextView selectedNumber;
    private TextView cancelBtn;
    private TextView confirmBtn;
    private TextView title;
    private TextView selectAllBtn;
    private ArrayList<Integer> mIndexList;
    private Builder builder;
    private RecyclerView recyclerView;

    public static class Builder {
        private Activity activity;

        private ArrayList<String> data;
        private String title;
        private String confirmText;
        private String cancelText;
        private boolean isOutsideTouchable;

        public Builder setOnCancelListener(OnCancelClickListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        private OnCancelClickListener onCancelListener;

        public Builder setOnConfirmListener(OnConfirmClickListener onConfirmListener) {
            this.onConfirmListener = onConfirmListener;
            return this;
        }

        private OnConfirmClickListener onConfirmListener;
        //        private OnConfirmClickListener onConfirmListener;
        private int confirmTextColor;
        private int cancelTextColor;
        private int titleTextColor;

        public interface OnCancelClickListener {
            void onCancelClick(PopupWindow popup);
        }

        public interface OnConfirmClickListener {
            void onConfirmClick(PopupWindow popup, ArrayList data, int selectedNumber);
        }

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setData(ArrayList<String> data) {
            this.data = data;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setConfirmText(String confirmText) {
            this.confirmText = confirmText;
            return this;
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setConfirmTextColor(int confirmTextColor) {
            this.confirmTextColor = confirmTextColor;
            return this;
        }

        public Builder setCancelTextColor(int cancelTextColor) {
            this.cancelTextColor = cancelTextColor;
            return this;
        }

        public Builder setTitleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public MultiSelectPopWindow build() {
            return new MultiSelectPopWindow(this);
        }
    }

    private MultiSelectPopWindow(final Builder builder) {
        this.builder = builder;
        View popView = View.inflate(builder.activity, R.layout.multi_select_list_popwindow, null);
        popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popwindow_multi_select);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        initView(popView);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        if (builder.onCancelListener != null) {
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.onCancelListener.onCancelClick(popupWindow);
                }
            });
        }
        if (builder.onConfirmListener != null) {
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("MultiSelectPopWindow.onClick confirmBtn");
                    ArrayList data = new ArrayList() ;
                    builder.onConfirmListener.onConfirmClick(popupWindow,data,4);
                }
            });
        }

//        adapter.setOnItemClickLister(new BaseRecyclerAdapter.OnItemClickLister<String>() {
//            @Override
//            public void onItemClick(int position, String data) {
//                adapter.getHashMap().get(position).setBackgroundResource(R.drawable.list_text_color_selector);
                adapter.notifyDataSetChanged();
//            }
//        });
    }

    private void initView(View view) {
        cancelBtn = (TextView) view.findViewById(R.id.cancel);
        title = (TextView) view.findViewById(R.id.title);
        selectAllBtn = (TextView) view.findViewById(R.id.all);
        confirmBtn = (TextView) view.findViewById(R.id.confirm);
        recyclerView = (RecyclerView) view.findViewById(R.id.multi_select_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(builder.activity.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiSelectListAdapter();
        System.out.println("builder.data = " + builder.data);
        adapter.setDatas(builder.data);
        recyclerView.setAdapter(adapter);

        setText(title, builder.title);
        setText(cancelBtn, builder.cancelText);
        setText(confirmBtn, builder.confirmText);

        setTextColor(title, builder.titleTextColor);
        setTextColor(cancelBtn, builder.cancelTextColor);
        setTextColor(confirmBtn, builder.confirmTextColor);

    }

    /**
     * 文本设置数据
     *
     * @param textView 文本
     * @param string   数据
     */
    private void setText(TextView textView, String string) {
        if (textView != null && string != null) {
            textView.setText(string);
        }
    }

    /**
     * 文本字体设置颜色
     *
     * @param textView 文本
     * @param color    颜色
     */
    private void setTextColor(TextView textView, int color) {
        if (textView != null && color != 0) {
            textView.setTextColor(color);
        }
    }

    /**
     * 设置弹窗位置
     *
     * @param parent view
     */
    public void show(View parent) {
        if (popupWindow != null) {
            System.out.println("0.5f");
            backgroundAlpha(0.5f);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }
    }

//    public void dismiss() {
//        if (popupWindow != null) {
//            popupWindow.dismiss();
//        }
//    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        try {
            WindowManager.LayoutParams lp = builder.activity.getWindow().getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0
            builder.activity.getWindow().setAttributes(lp);

            System.out.println("背景色设置成功 = ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
