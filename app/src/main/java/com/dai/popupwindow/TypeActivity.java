package com.dai.popupwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dai.popupwindow.fragment.BottomFragment;
import com.dai.popupwindow.fragment.MultiFragment;
import com.dai.popupwindow.fragment.SuspensionFragment;
import com.dai.popupwindow.util.PopupType;

/**
 * Created by dai on 2017/5/5.
 */

public class TypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        int popupType = getIntent().getIntExtra("popupType", -1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (popupType) {
            case PopupType.MULTI:
                fragment = new MultiFragment();
                break;
            case PopupType.BOTTOM:
                fragment = new BottomFragment();
                break;
            case PopupType.SUSPENSION:
                fragment = new SuspensionFragment();
                break;
        }
        transaction.replace(R.id.fragment, fragment).commit();
    }
}
