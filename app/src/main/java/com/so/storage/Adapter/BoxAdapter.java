package com.so.storage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.DTO.BoxDTO;
import com.so.storage.FragReservationCheck;
import com.so.storage.MainActivity;
import com.so.storage.OnSingerItemClickListnerBox;
import com.so.storage.R;

import java.util.ArrayList;

public class BoxAdapter
        extends RecyclerView.Adapter<BoxAdapter.ViewHolder>
        implements OnSingerItemClickListnerBox {

    Context context;
    ArrayList<BoxDTO> dtos;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;
    OnSingerItemClickListnerBox listner;
    //메인액티비티에서 접근할수있게 만들어줌.

    //선언만 한상태 . null
    public BoxAdapter(Context context, ArrayList<BoxDTO> dtos, MainActivity mActivity){
        this.context = context;
        this.dtos = dtos;
        this.mActivity = mActivity;
        //선언 후 초기화 된 상태.
        //값을 사용할수있는상태
    }

    // 화면(xml)연결 ListView에서 ViewHolder를 사용한 경우 똑같음.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_cardview_box , parent , false);
        fragReservationCheck = new FragReservationCheck();

        return new ViewHolder(itemview);
    }

    //데이터 연결부(Binding)
    //Viewholder가 세팅 되어있는 상태에서 viewHolder를 인자로 받아서 사용
    //이벤트 처리 onClick , 전부 여기서 처리
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoxDTO dto = dtos.get(position);
        holder.setItem(dto);

        holder.reser_imgv_box_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragReservationCheck);

            }
        });
        //holder부분은 우리가 만들어놓은 ViewHolder에서 작업한다.

    }
    //Item의 갯수가 어느정도 들어가는지
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void addDto(BoxDTO dto){dtos.add(dto);}

    public void setOnItemClickListner(OnSingerItemClickListnerBox listner){
        this.listner = listner;
    }

    //오버라이드 된 이벤트 처리 부분
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listner != null){
            listner.onItemClick(holder , view,position);
        }
    }

    public BoxDTO getItem(int position) {
        return dtos.get(position);
    }


    //ViewHolder를 강제로 만들게끔 처리된 부분.
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parentLay;
        TextView box_name , box_info;
        ImageView reser_imgv_box_click;
        //find안된 상태 null

        //연결하는 부분
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // parentLay = itemView.findViewById(R.id.)
            box_name = itemView.findViewById(R.id.box_name);
            box_info = itemView.findViewById(R.id.box_info);
            reser_imgv_box_click = itemView.findViewById(R.id.reser_imgv_box_click);
        }
        //데이터 세팅 부분 onBindViewHolder에서 사용함
        //데이터 의존성
        public void setItem(BoxDTO dto){
            box_info.setText(dto.getBox_info());
            box_name.setText(dto.getBox_info());
            reser_imgv_box_click.setImageResource(dto.getClick_img());
        }
    }
}
