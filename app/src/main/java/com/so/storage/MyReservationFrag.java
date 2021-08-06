package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.Adapter.reservationAdapter;
import com.so.storage.DTO.ReservationDTO;

import java.util.ArrayList;

public class MyReservationFrag extends Fragment {

    MainActivity mActivity;
    ViewGroup rootView;
    ListView listv_reservation;
    reservationAdapter adapter;
    ArrayList<ReservationDTO> dtos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_reservation, container, false);
        mActivity = (MainActivity) getActivity();

        listv_reservation = rootView.findViewById(R.id.listv_reservation);

        dtos = new ArrayList<>();

        adapter = new reservationAdapter();

        adapter.addDto(new ReservationDTO("C3", "2021/07/01", "2021/09/01", "광주점"));
        adapter.addDto(new ReservationDTO("B3", "2021/07/01", "2021/09/01", "광주점"));

        listv_reservation.setAdapter(adapter);

        return rootView;
    } // onCreateView()
} // class