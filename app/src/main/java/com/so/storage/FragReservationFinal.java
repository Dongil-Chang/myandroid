package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragReservationFinal extends Fragment {

    MainActivity mActivity;
    ViewGroup rootView;
    FragPrntMyPage fragPrntMyPage;
    FragMainPage fragMainPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_final, container, false);
        mActivity = (MainActivity) getActivity();
        fragPrntMyPage = new FragPrntMyPage(7);
        fragMainPage = new FragMainPage();

        rootView.findViewById(R.id.btn_reser_final_mg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragPrntMyPage);
            }
        });
        rootView.findViewById(R.id.btn_reser_final_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragMainPage);
            }
        });
        return rootView;
    }
}