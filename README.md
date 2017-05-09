# MultiPopupWindow
    一个可以从底部弹出PopupWindow多选弹出窗，返回选中的item的position与data
# Java代码
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
## 使用效果展示
    ![Image] (https://github.com/Sotardust/popupwindow/raw/master/gif/device-2017-05-09-210110.png)