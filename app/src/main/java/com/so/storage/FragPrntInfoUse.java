package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class FragPrntInfoUse extends Fragment  implements View.OnClickListener  {

    TabLayout infopageTab;
    FragInfoStorage fragInfoStorage;
    FragInfoIot fragInfoIot;
    FragInfoReservation fragInfoReservation;
    Fragment selected = null;

    ViewGroup rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_prnt_info_use, container, false);

        fragInfoStorage = new FragInfoStorage();    // 창고 소개
        fragInfoIot = new FragInfoIot();            // IoT 기능 안내
        fragInfoReservation = new FragInfoReservation();   // 예약 방법

        getChildFragmentManager().beginTransaction().replace(R.id.icontainer, fragInfoStorage).addToBackStack(null).commit();

        infopageTab = rootView.findViewById(R.id.infopageTab);
        infopageTab.addTab(infopageTab.newTab().setText("창고소개"));
        infopageTab.addTab(infopageTab.newTab().setText("IoT기능 안내"));
        infopageTab.addTab(infopageTab.newTab().setText("예약방법"));

        infopageTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int temp_pos = tab.getPosition();
                if(temp_pos == 0) {
                    selected = fragInfoStorage;
                } else if(temp_pos == 1) {
                    selected = fragInfoIot;
                } else if(temp_pos == 2) {
                    selected = fragInfoReservation;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.icontainer, selected).commit();
            } // onTabSelected()

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return rootView;
    } // onCreateView()

    @Override
    public void onClick(View v) {

    }
} // End of class
