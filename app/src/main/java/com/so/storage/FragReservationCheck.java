package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragReservationCheck extends Fragment {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationFinal fragReservationFinal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_check, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationFinal = new FragReservationFinal();
        rootView.findViewById(R.id.btn_reser_check_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationFinal);
            }
        });


        rootView.findViewById(R.id.btn_reser_check_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        return rootView;
    } // onCreateView()
}