package com.so.storage.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.so.storage.DTO.ReservationDTO;
import com.so.storage.R;

import java.util.ArrayList;

public class reservationAdapter extends BaseAdapter {

    private ArrayList<ReservationDTO> dtos = new ArrayList<>();
    private ReservationDTO dto;

    public reservationAdapter(ReservationDTO dto) {
        this.dto = dto;
    }

    public reservationAdapter() {}

    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        Button listv_reservation_cancel;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listv_view_reservation, parent, false);
        }

        listv_reservation_cancel = convertView.findViewById(R.id.listv_reservation_cancel);
        listv_reservation_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("예약을 취소하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        TextView product_type, booking_start, booking_end, location_id;
        product_type = (TextView) convertView.findViewById(R.id.product_type);
        booking_start = (TextView) convertView.findViewById(R.id.booking_start);
        booking_end = (TextView) convertView.findViewById(R.id.booking_end);
        location_id = (TextView) convertView.findViewById(R.id.location_id);

        ReservationDTO dto = dtos.get(position);

        product_type.setText(dto.getProduct_type());
        booking_start.setText(dto.getBooking_start());
        booking_end.setText(dto.getBooking_end());
        location_id.setText(dto.getLocation_id());

        return convertView;
    }

    public void addDto(ReservationDTO dto) {
        dtos.add(dto);
    }


}
