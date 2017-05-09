# popupwindow
多种弹窗
#Java代码
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
