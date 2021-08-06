package com.so.storage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class FragPrntMyPage extends Fragment  implements View.OnClickListener  {
    private static final String TAG = "main:FragPrntMyPage";

    TabLayout mypageTab;
    MyStorageFrag myStorageFrag;
    MyReservationFrag myReservationFrag;
    MyAccountFrag myAccountFrag;
    MyAccountSubFrag myAccountSubFrag;
    MyMemberLeaveFrag myMemberLeaveFrag;
    Fragment selected = null;

    ViewGroup rootView;

    int callNum;

    public FragPrntMyPage(int callNum) {
        this.callNum = callNum;
        Log.d(TAG, "FragPrntMyPage: " + callNum);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_prnt_mypage, container, false);

        myStorageFrag = new MyStorageFrag();            // 내 창고
        myReservationFrag = new MyReservationFrag();    // 예약내역
        myAccountFrag = new MyAccountFrag();            // 비밀번호 확인(정보수정 페이지 전환 전)
        myAccountSubFrag = new MyAccountSubFrag();      // 정보수정
        myMemberLeaveFrag = new MyMemberLeaveFrag();    // 회원탈퇴

        getChildFragmentManager().beginTransaction().replace(R.id.mcontainer, myStorageFrag).commit();

        mypageTab = rootView.findViewById(R.id.mypageTab);
        mypageTab.addTab(mypageTab.newTab().setText("내창고"));
        mypageTab.addTab(mypageTab.newTab().setText("예약내역"));
        mypageTab.addTab(mypageTab.newTab().setText("정보수정"));
        mypageTab.addTab(mypageTab.newTab().setText("회원탈퇴"));

        mypageTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int temp_pos = tab.getPosition();
                if(temp_pos == 0) {
                    selected = myStorageFrag;
                } else if(temp_pos == 1) {
                    selected = myReservationFrag;
                } else if(temp_pos == 2) {
                    selected = myAccountSubFrag;
                } else if(temp_pos == 3) {
                    selected = myMemberLeaveFrag;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.mcontainer, selected).commit();
            } // onTabSelected()

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        if(callNum == 7){
            TabLayout.Tab tab = mypageTab.getTabAt(1);
            tab.select();
            callNum = -1;
        }

        return rootView;
    } // onCreateView()

    @Override
    public void onClick(View v) {

    }
} // End of class
