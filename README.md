# **MultiPopupWindow**
    一个可以从底部弹出PopupWindow的多选弹出窗，返回选中的item的position与data
## 动态效果图展示
![image](https://github.com/Sotardust/popupwindow/blob/master/gif/popupWindow.gif)

## 使用方式

## 添加Gradle依赖

     dependencies{
         compile 'com.dai.library:popupWindow:1.0.0'
     }

## 创建实例及配置
### 1、创建popupWindow弹出窗实例
    MultiSelectPopWindow popWindow = new MultiSelectPopWindow.Builder(getActivity()).build();
### 2、参数配置以及使用方式
    MultiSelectPopWindow popWindow = new MultiSelectPopWindow.Builder(getActivity())
                            .setTitle("标题")      //设置标题
                            .setCancelText("取消")  //设置取消按钮
                            .setConfirmText("确认")  //设置确认按钮
                            .setData(ArrayList<String> data)           //绑定ArrayList<String>类型数据
                            .setCancelTextColor(int color)  //设置取消按钮的字体颜色
                            .setItemSelectedTextColor(int color) //设置选中item对应的字体颜色
                            .setNumberBackgroundColor(R.drawable.xxx)  //设置选中item条数的背景色
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
                    popWindow.show(View view);
# **PopupMenu**
    一个可以高度定制的弹出式菜单
    
## 动态效果图展示
![image](https://github.com/Sotardust/popupwindow/blob/master/gif/popupWindow.gif)
## 弹出菜单的使用方式
    PopupMenu popupMenu = new PopupMenu.Builder(getActivity())
                 .setAdapter(adapter)  //必须设置选项 设置适配器
                 .setView(view)        //必须设置选项  设置视图view
                 .setBackground(R.color.colorPrimary)   //设置弹出菜单的背景色，可设置点9patch图片
                 .setMonospaced(true)    //设置弹出菜单宽度与view宽度等宽
                 .setWidth(500)      //自定义弹出菜单宽度
                 .build();
## 设置适配器
### 1、使用默认适配器
       PopupMenuAdapter adapter = new PopupMenuAdapter();
       adapter.setData(ArrayList<String> data) ;
### 2、使用自定义适配器 
       //继承已封装的BaseRecyclerAdapter<T> 
       public class PopupMenuAdapter extends BaseRecyclerAdapter<String> {}
       
       //或者继承RecyclerView.Adapter<RecyclerView.ViewHolder>
        public class PopupMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {}
       
### 1、设置弹出式菜单显示
       popupMenu.show() ;
### 2、设置弹出式菜单与视图view 的偏移量
       popupMenu.show(int xoff,int yoff) ; //xoff:与view在x轴的偏移量，yoff:与view在y轴的偏移量
### 3、设置弹出式菜单从顶部弹出
       popupMenu.showTop()
    