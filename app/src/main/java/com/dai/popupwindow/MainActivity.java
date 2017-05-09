package com.dai.popupwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dai.library.util.BaseRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        MyAdapter myAdapter = new MyAdapter();
        myAdapter.setData(data);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickLister(new BaseRecyclerAdapter.OnItemClickLister<String>() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TypeActivity.class);
                intent.putExtra("popupType", position);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        data.add("底部弹出多选框");
        data.add("某控件下弹窗");
        data.add("屏幕中间弹窗");
    }
}
