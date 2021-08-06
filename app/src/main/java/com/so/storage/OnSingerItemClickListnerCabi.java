package com.so.storage;

import android.view.View;

import com.so.storage.Adapter.CabiAdapter;

public interface OnSingerItemClickListnerCabi {

    public void onItemClick(CabiAdapter.ViewHolder holder,
                            View view , int position);
}
