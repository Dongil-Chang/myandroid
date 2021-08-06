package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;


// 로그인 페이지 내 아이디/패스워드 찾기 페이지
public class FragPrntIdPwFind extends Fragment implements View.OnClickListener {

    TabLayout tabs;
    IdFindFrag idfrag;
    PwFindFrag pwfrag;
    EmailFrag emailfrag;
    Fragment selected = null;
    
    ViewGroup rootView;


    /*public static FragPrntIdPwFind newInstance() {
        return new FragPrntIdPwFind();
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_prnt_idpwfind, container, false);

        idfrag = new IdFindFrag();
        pwfrag = new PwFindFrag();
        emailfrag = new EmailFrag();

        getChildFragmentManager().beginTransaction().replace(R.id.fcontainer, idfrag).addToBackStack(null).commit();
        tabs = rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("ID 찾기"));
        tabs.addTab(tabs.newTab().setText("PW 찾기"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int temp_pos = tab.getPosition();
                if (temp_pos == 0) {
                    selected = idfrag;
                } else if (temp_pos == 1) {
                    selected = pwfrag;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.fcontainer, selected).addToBackStack(null).commit();
            }

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

        /*Fragment fg;
        switch (v.getId()) {
            case R.id.:
                fg = ChildOneFragment.newInstance();
                setChildFragment(fg);
                break;
            case R.id.btn_tow:
                fg = ChildTowFragment.newInstance();
                setChildFragment(fg);
                break;
        }
*/
    } // onClick()
/*
    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    } // set*/
} // class
