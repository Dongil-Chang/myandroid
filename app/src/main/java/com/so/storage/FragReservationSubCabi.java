package com.so.storage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.Adapter.CabiAdapter;
import com.so.storage.DTO.CabiDTO;

import java.util.ArrayList;


public class FragReservationSubCabi extends Fragment {
    RecyclerView recyclerView;
    CabiAdapter adapter;
    ArrayList<CabiDTO> dtos;
    private Context context;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_reservation_sub_cabi, container, false);
        context = container.getContext();
        fragReservationCheck = new FragReservationCheck();
        mActivity = (MainActivity) getActivity();
        dtos = new ArrayList<>();//

        recyclerView = view.findViewById(R.id.reser_cabi_rv);
        //레이아웃 매니저를 통해 레이아웃 형태를 지정하고
        //recyclerView에 setting해줌
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CabiAdapter(context, dtos, mActivity);
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C1",
                "C1", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C2",
                "C2", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C3",
                "C3", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C4",
                "C4", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C5",
                "C5", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C6",
                "C6", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C7",
                "C7", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C8",
                "C8", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C9",
                "C9", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new CabiDTO(R.drawable.cabi, "C10",
                "C10", R.drawable.plusimg, R.drawable.click)
        );


        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(new OnSingerItemClickListnerCabi() {
            @Override
            public void onItemClick(CabiAdapter.ViewHolder holder, View view, int position) {
                mActivity.onFragmentChange(fragReservationCheck);
            }
        });

        view.findViewById(R.id.btn_reser_cabi_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        return view;
    }
} // End of class