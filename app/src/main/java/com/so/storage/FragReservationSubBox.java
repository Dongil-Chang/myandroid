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

import com.so.storage.Adapter.BoxAdapter;
import com.so.storage.DTO.BoxDTO;

import java.util.ArrayList;


public class FragReservationSubBox extends Fragment {
    RecyclerView recyclerView;
    BoxAdapter adapter;
    ArrayList<BoxDTO> dtos;
    private Context context;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_reservation_sub_box, container, false);
        context = container.getContext();
        fragReservationCheck = new FragReservationCheck();
        mActivity = (MainActivity) getActivity();
        dtos = new ArrayList<>();//

        recyclerView = view.findViewById(R.id.reser_box_rv);
        //레이아웃 매니저를 통해 레이아웃 형태를 지정하고
        //recyclerView에 setting해줌
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new BoxAdapter(context, dtos, mActivity);
        adapter.addDto(new BoxDTO(R.drawable.box, "B1",
                "B1", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B2",
                "B2", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B3",
                "B3", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B4",
                "B4", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B5",
                "B5", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B6",
                "B6", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B7",
                "B7", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B8",
                "B8", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B9",
                "B9", R.drawable.plusimg, R.drawable.click)
        );
        adapter.addDto(new BoxDTO(R.drawable.box, "B10",
                "B10", R.drawable.plusimg, R.drawable.click)
        );


        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(new OnSingerItemClickListnerBox() {
            @Override
            public void onItemClick(BoxAdapter.ViewHolder holder, View view, int position) {
                mActivity.onFragmentChange(fragReservationCheck);
            }
        });

        view.findViewById(R.id.btn_reser_box_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });



        return view;
    }
} // End of class