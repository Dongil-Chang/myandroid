package com.so.storage;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


// 마이 페이지 내 개인 정보 수정 실제 페이지
public class MyAccountFrag extends Fragment {
    MainActivity mActivity;
    DatePicker datePicker_account;
    EditText edt_account_pw, edt_account_pwchk, edt_account_name, edt_account_email, edt_account_tel;
    TextView txtv_account_pw, txtv_account_pwchk, txtv_account_name, txtv_account_email, txtv_account_tel;
    Context context;

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mActivity.account_validityChk();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_account, container, false);
        mActivity = (MainActivity) getActivity();
        Fragment fragMainPage = new FragMainPage();

        edt_account_pw = rootView.findViewById(R.id.edt_account_pw);
        edt_account_pwchk = rootView.findViewById(R.id.edt_account_pwchk);
        edt_account_name = rootView.findViewById(R.id.edt_account_name);
        edt_account_email = rootView.findViewById(R.id.edt_account_email);
        edt_account_tel = rootView.findViewById(R.id.edt_account_tel);

        txtv_account_pw = rootView.findViewById(R.id.txtv_account_pw);
        txtv_account_pwchk = rootView.findViewById(R.id.txtv_account_pwchk);
        txtv_account_name = rootView.findViewById(R.id.txtv_account_name);
        txtv_account_email = rootView.findViewById(R.id.txtv_account_email);
        txtv_account_tel = rootView.findViewById(R.id.txtv_account_tel);

        datePicker_account = rootView.findViewById(R.id.datePicker_account);

        edt_account_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                mActivity.account_validityChk();
            }
        });

        edt_account_pw.addTextChangedListener(textWatcher);

        edt_account_pwchk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwchk = edt_account_pwchk.getText().toString();
                mActivity.accountPwChk(pwchk);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_account_email.addTextChangedListener(textWatcher);
        edt_account_tel.addTextChangedListener(textWatcher);



        return rootView;
    }



}
