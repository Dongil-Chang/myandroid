package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 마이 페이지 내 개인 정보 수정을 위한 비밀번호 확인 페이지
public class MyAccountSubFrag extends Fragment {
    MainActivity mActivity;
    Button btn_accountsub_ok;
    FragPrntIdPwFind fragPrntIdPwFind;
    MyAccountFrag myAccountFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        myAccountFrag = new MyAccountFrag();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_account_sub, container, false);
        btn_accountsub_ok = rootView.findViewById(R.id.btn_accountsub_ok);

        btn_accountsub_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(myAccountFrag);
            } // onClick()
        });

        return rootView;
    } // onCreateView()
} // End of class
