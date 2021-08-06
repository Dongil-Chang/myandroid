package com.so.storage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.R;

import java.util.ArrayList;

public class MemberListAdapter extends BaseAdapter {

    private ArrayList<MemberUserDTO> dtos = new ArrayList<>();
    private MemberUserDTO dto;

    public MemberListAdapter(MemberUserDTO dto) {
        this.dto = dto;
    }

    public MemberListAdapter() {}

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
            convertView = inflater.inflate(R.layout.mg_listv_view_member, parent, false);
        } // if

        TextView id, name, email, tel;
        id = (TextView) convertView.findViewById(R.id.id);
        name = (TextView) convertView.findViewById(R.id.name);
        email = (TextView) convertView.findViewById(R.id.email);
        tel = (TextView) convertView.findViewById(R.id.tel);

        MemberUserDTO dto = dtos.get(position);

        id.setText(dto.getId());
        name.setText(dto.getName());
        email.setText(dto.getEmail());
        tel.setText(dto.getTel());

        return convertView;
    } // getView()

    public void addDto(MemberUserDTO dto) {
        dtos.add(dto);
    } // addDto()
} // class
