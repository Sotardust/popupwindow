# MultiPopupWindow
    一个可以从底部弹出PopupWindow多选弹出窗，返回选中的item的position与data
## 效果图展示
![image](https://github.com/Sotardust/popupwindow/blob/master/gif/device-2017-05-09-210111.png)

# 使用方式

## 添加Gradle依赖

     dependencies{
         compile 'zlc.season:rxdownload2:2.0.0-beta4'
     }

## 创建实例及配置
### 1、创建popupWindow弹出窗实例
    MultiSelectPopWindow popWindow = new MultiSelectPopWindow.Builder(getActivity()).build();
### 2、参数配置以及使用方式
    MultiSelectPopWindow popWindow = new MultiSelectPopWindow.Builder(getActivity())
                            .setTitle("标题")      //设置标题
                            .setCancelText("取消")  //设置取消按钮
                            .setConfirmText("确认")  //设置确认按钮
                            .setData(data)           //绑定ArrayList<String>类型数据
                            .setCancelTextColor(Color.BLUE)  //设置取消按钮的字体颜色
                            .setItemSelectedTextColor(Color.BLUE) //设置选中item对应的字体颜色
                            .setNumberBackgroundColor(R.drawable.number_background)  //设置选中item条数的背景色
                            .setOnCancelListener(new MultiSelectPopWindow.Builder.OnCancelClickListener() {  //设置取消监听事件
                                @Override
                                public void onCancelClick(PopupWindow popup) {
                                    popup.dismiss();  //取消弹窗
                                }
                            })
                            .setOnConfirmListener(new MultiSelectPopWindow.Builder.OnConfirmClickListener() {//设置确认监听事件
                                @Override
                                public void onConfirmClick(PopupWindow popup, HashMap<Object, String> data) {
                                    //HashMap<Object, String> object:选中item的position String：选中item对应的数据
                                    popup.dismiss();

                                }
                            })
                            .build();
                    popWindow.show(getView());
