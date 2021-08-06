package com.so.storage;

import android.view.View;

import com.so.storage.Adapter.BoxAdapter;

public interface OnSingerItemClickListnerBox {

    public void onItemClick(BoxAdapter.ViewHolder holder,
                            View view , int position);
}
