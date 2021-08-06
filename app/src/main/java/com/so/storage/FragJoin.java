package com.so.storage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.so.storage.ATask.IdCheck;
import com.so.storage.ATask.JoinInsert;
import com.so.storage.DTO.MemberUserDTO;

import static com.so.storage.MainActivity.loginDTO;

public class FragJoin extends Fragment {
    MainActivity mActivity;
    Button btn_join_back, btn_postsearch, btn_join_submit, btn_join_idChk;
    int year = 0, month = 0, day = 0;
    String date;
    EditText edt_join_id, edt_join_pw, edt_join_pwchk, edt_join_name, edt_join_email, edt_join_tel;
    TextView txtv_join_id, txtv_join_pw, txtv_join_pwchk, txtv_join_name, txtv_join_email, txtv_join_tel;
    CheckBox chkBox_join_agree1, chkBox_join_agree2;
    FragLogin fragLogin;
    FragMainPage fragMainPage;

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mActivity.join_validityChk();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.frag_join, container, false);
        mActivity = (MainActivity) getActivity();
        Fragment fragMainPage = new FragMainPage();
        btn_join_back = rootView.findViewById(R.id.btn_join_back);
        //btn_postsearch = rootView.findViewById(R.id.btn_postsearch);
        btn_join_submit = rootView.findViewById(R.id.btn_join_submit);
        //DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);

        edt_join_id = rootView.findViewById(R.id.edt_join_id);
        edt_join_pw = rootView.findViewById(R.id.edt_join_pw);
        edt_join_pwchk = rootView.findViewById(R.id.edt_join_pwchk);
        edt_join_name = rootView.findViewById(R.id.edt_join_name);
        edt_join_email = rootView.findViewById(R.id.edt_join_email);
        edt_join_tel = rootView.findViewById(R.id.edt_join_tel);

        txtv_join_id = rootView.findViewById(R.id.txtv_join_id);
        txtv_join_pw = rootView.findViewById(R.id.txtv_join_pw);
        txtv_join_pwchk = rootView.findViewById(R.id.txtv_join_pwchk);
        txtv_join_name = rootView.findViewById(R.id.txtv_join_name);
        txtv_join_email = rootView.findViewById(R.id.txtv_join_email);
        txtv_join_tel = rootView.findViewById(R.id.txtv_join_tel);

        chkBox_join_agree1 = rootView.findViewById(R.id.chkBox_join_agree1);
        chkBox_join_agree2 = rootView.findViewById(R.id.chkBox_join_agree2);

        btn_join_idChk = rootView.findViewById(R.id.btn_join_idChk);

        edt_join_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                mActivity.join_validityChk();
            }
        });

        edt_join_pw.addTextChangedListener(textWatcher);

        edt_join_pwchk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwchk = edt_join_pwchk.getText().toString();
                mActivity.joinPwChk(pwchk);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_join_name.addTextChangedListener(textWatcher);
        edt_join_email.addTextChangedListener(textWatcher);
        edt_join_tel.addTextChangedListener(textWatcher);

        btn_join_idChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_join_id.getText().toString();
                IdCheck idCheck = new IdCheck(id);

                try {
                    idCheck.execute().get();

                    if(loginDTO != null) {
                        if(id.equals(loginDTO.getId())) {
                            Snackbar.make(v, "이미 존재하는 아이디입니다.", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(v, "사용 가능한 아이디입니다.", Snackbar.LENGTH_LONG).show();
                    }//if

                } catch (Exception e) {

                }
            }
        });

        /*datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "/" + monthOfYear + "/" + dayOfMonth;
            } // onDateChanged()
        });*/

        /*// 우편번호 검색 버튼 클릭 이벤트
        btn_postsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            } // onClick()
        });*/

        btn_join_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "스낵바", Snackbar.LENGTH_LONG).show();
                if(edt_join_id.getText().toString().isEmpty() || edt_join_pw.getText().toString().isEmpty() ||
                        edt_join_name.getText().toString().isEmpty() || edt_join_email.getText().toString().isEmpty() || edt_join_tel.getText().toString().isEmpty()) {
                    Snackbar.make(v, "필수 정보를 입력하지 않으셨습니다.", Snackbar.LENGTH_LONG).show();
                } else if(!txtv_join_id.getText().toString().isEmpty() || !txtv_join_pw.getText().toString().isEmpty() ||
                        !txtv_join_name.getText().toString().isEmpty() || !txtv_join_email.getText().toString().isEmpty() || !txtv_join_tel.getText().toString().isEmpty()) {
                    Snackbar.make(v, "형식에 맞게 입력하셔야 합니다.", Snackbar.LENGTH_LONG).show();
                } else if(txtv_join_pwchk.getText().toString().equals("동일한 비밀번호를 입력하세요")) {
                    Snackbar.make(v, "비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_LONG).show();
                } else if(!chkBox_join_agree1.isChecked() || !chkBox_join_agree2.isChecked()) {
                    Snackbar.make(v, "이용약관에 모두 동의해주셔야 합니다.", Snackbar.LENGTH_LONG).show();
                } else {
                    //Snackbar.make(v, "회원가입이 완료되었습니다.", Snackbar.LENGTH_LONG).show();
                    //member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, commcode, subcode;
                    MemberUserDTO dto = new MemberUserDTO(
                            edt_join_id.getText().toString(),
                            edt_join_pw.getText().toString(),
                            edt_join_name.getText().toString(),
                            edt_join_email.getText().toString(),
                            edt_join_tel.getText().toString()
                    );
                    JoinInsert joinInsert = new JoinInsert(dto);
                    try {
                        joinInsert.execute().get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(v, "회원가입이 완료되었습니다.", Snackbar.LENGTH_LONG).show();

                    fragLogin = new FragLogin();
                    mActivity.onFragmentChange(fragLogin);
                }
            }
        });

        // 회원가입 페이지 x 뒤로 가기 버튼 클릭시 메인 화면으로 돌아가는 이벤트
        btn_join_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragMainPage);
            } // onClick()
        }); // btn_login_back
        return rootView;
    } // onCreateView


} // class