package com.so.storage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.DTO.NoticeBoardDTO;
import com.so.storage.R;
import com.so.storage.animations.Animations;
import com.so.storage.databinding.NoticeItemExpandBinding;

import java.util.List;


public class NoticeExpendableRecyclerViewAdapter extends RecyclerView.Adapter<NoticeExpendableRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<NoticeBoardDTO> noticeList;

    // 생성자
    public NoticeExpendableRecyclerViewAdapter (Context context, List<NoticeBoardDTO> list) {
        this.context = context;
        this.noticeList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // Fragment의 view 생성시키는 방법
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item_expand, parent, false);
        NoticeItemExpandBinding bi = DataBindingUtil.bind(view);
        return new ViewHolder(bi);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

        holder.bi.name.setText(noticeList.get(i).getBoard_title());

        // Picasso.get().load(personList.get(i).getImage()).into(holder.bi.image);

        holder.bi.viewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!noticeList.get(i).isExpanded(), v, holder.bi.layoutExpand);
                noticeList.get(i).setExpanded(show);
            }
        });

    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {

        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;
    } // toggleLayout()


    @Override
    public int getItemCount() {
        return noticeList.size();
    } // getItemCount()

    public class ViewHolder extends RecyclerView.ViewHolder {
        NoticeItemExpandBinding bi;

        public ViewHolder(@NonNull NoticeItemExpandBinding itemView) {
            super(itemView.getRoot());

            bi = itemView;
        }
    } // ViewHoder Class
}