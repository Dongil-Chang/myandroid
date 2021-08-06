package com.so.storage.Manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.Adapter.MemberListAdapter;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.MainActivity;
import com.so.storage.R;

import java.util.ArrayList;

public class FragMgMemberList extends Fragment {

    ViewGroup rootView;
    MainActivity mActivity;
    ListView listv_mg_member_list;
    MemberListAdapter adapter;
    ArrayList<MemberUserDTO> dtos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.frag_mg_member_list, container, false);
        mActivity = (MainActivity) getActivity();
        listv_mg_member_list = rootView.findViewById(R.id.listv_mg_member_list);
        dtos = new ArrayList<>();
        adapter = new MemberListAdapter();

        MainActivity.loginDTO.getId();
        /*adapter.addDto(new MemberUserDTO("1", "hanul", "1234", "홍길동", "abc@naver.com", "광주광역시", "010-1234-5678", "2000/07/01", "", "", "2021/07/01", "1", "1"));
        adapter.addDto(new MemberUserDTO("1", "hanul2", "1234", "이순신", "bcd@naver.com", "광주광역시", "010-9632-8547", "2001/06/04", "", "", "2021/07/01", "1", "1"));

        listv_mg_member_list.setAdapter(adapter);*/

        return rootView;
    } // onCreateView
} // class
