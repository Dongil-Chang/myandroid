package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.so.storage.Adapter.NoticeExpendableRecyclerViewAdapter;
import com.so.storage.DTO.NoticeBoardDTO;
import com.so.storage.databinding.FragNoticeBinding;

import java.util.ArrayList;
import java.util.List;

public class FragNotice extends Fragment {

    ViewGroup rootView;
    private FragNoticeBinding bi = null;
    NoticeExpendableRecyclerViewAdapter adapter;
    List<NoticeBoardDTO> noticeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         // rootView = (ViewGroup) inflater.inflate(R.layout.frag_notice, container, false);
        // bi = DataBindingUtil.setContentView(getActivity(), R.layout.frag_notice);
        bi = DataBindingUtil.inflate(inflater, R.layout.frag_notice, container, false);
        init();
        return bi.getRoot();
    } // onCrateView()

    private void init() {
        noticeList = new ArrayList<>();
        noticeList = getNoticeData();
        noticeList.addAll(getNoticeData());
        adapter = new NoticeExpendableRecyclerViewAdapter(getContext(), noticeList);
        bi.list.setLayoutManager(new LinearLayoutManager(getContext()));
        bi.list.setHasFixedSize(true);
        bi.list.setAdapter(adapter);
    }

    private List<NoticeBoardDTO> getNoticeData() {
        List<NoticeBoardDTO> list = new ArrayList<>();

        String[] titles = getResources().getStringArray(R.array.title);
        // TypedArray images = getResources().obtainTypedArray(R.array.images);

        for (int i = 0; i < titles.length; i++) {
            NoticeBoardDTO dto = new NoticeBoardDTO();
            dto.setBoard_title(titles[i]);
            // notice.setImage(images.getResourceId(i, -1));
            list.add(dto);
        }

        return list;

    }
} // class
