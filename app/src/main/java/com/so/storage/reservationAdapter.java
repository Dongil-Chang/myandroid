package com.so.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.so.storage.DTO.ReservationDTO;

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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listv_view_reservation, parent, false);
        }

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
