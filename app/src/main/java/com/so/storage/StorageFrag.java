package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StorageFrag extends Fragment {
    MainActivity mActivity;
    Button justcheckBtn_1;
    EmptyStorageFrag emptyStorageFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_storage, container, false);

        mActivity = (MainActivity) getActivity();
        emptyStorageFrag = new EmptyStorageFrag();

        justcheckBtn_1 = rootView.findViewById(R.id.justcheckBtn_1);

        justcheckBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(emptyStorageFrag);
            }
        });

        return rootView;
    }
}
