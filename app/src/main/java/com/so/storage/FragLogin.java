package com.so.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.so.storage.ATask.LoginSelect;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.common.SaveLogin;

import static com.so.storage.MainActivity.loginDTO;

public class FragLogin extends Fragment {
    private static final String TAG = "";
    Button btn_login_join, btn_login_IdPwFind, btn_login, btn_login_back;
    MainActivity mActivity;
    ViewGroup rootView;
    FragJoin fragJoin;
    FragPrntIdPwFind fragPrntIdPwFind;
    FragMainPage fragMainPage;
    EditText edt_login_id, edt_login_pw;
    OAuthLogin mOAuthLoginModule;
    Context mContext;
    ImageButton naver_login,kakao_login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragPrntIdPwFind = new FragPrntIdPwFind();
        fragJoin = new FragJoin();
        fragMainPage = new FragMainPage();

        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_login, container, false);

        mActivity = (MainActivity) getActivity();

        btn_login_join = rootView.findViewById(R.id.btn_login_join);
        btn_login_IdPwFind = rootView.findViewById(R.id.btn_login_IdPwFind);
        btn_login = rootView.findViewById(R.id.btn_login);
        btn_login_back = rootView.findViewById(R.id.btn_login_back);
        edt_login_id = rootView.findViewById(R.id.edt_login_id);
        edt_login_pw = rootView.findViewById(R.id.edt_login_pw);
        naver_login = rootView.findViewById(R.id.naver_login);
        kakao_login = rootView.findViewById(R.id.kakao_login);


        //회원가입버튼
        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mActivity.onFragmentChange(fragJoin);
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("회원가입 하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.onFragmentChange(fragJoin);
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 아이디/비밀번호 찾기 액티비티 → 프래그먼트로 변경
        btn_login_IdPwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragPrntIdPwFind); // 프래그먼트 전환 메소드 호출
            } // onClick()
        });

        //로그인 버튼
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberUserDTO dto = new MemberUserDTO();
                String id = edt_login_id.getText().toString();
                String pw = edt_login_pw.getText().toString();
                //dto.setId(id);
                //dto.setPw(pw);
                LoginSelect loginSelect = new LoginSelect(id, pw);
                try {
                    String result = loginSelect.execute().get();
                    // Log.d(TAG, "login onClick: " + result + ", " + loginDTO.getId()) ;

                    //if(!loginDTO.getId().equals("")) {
                    if(loginDTO != null) {
                        if (id.equals(loginDTO.getId()) && pw.equals(loginDTO.getPw())) {
                            Snackbar.make(v, "로그인 되었습니다.", Snackbar.LENGTH_LONG).show();
                            mActivity.onFragmentChange(fragMainPage);
                            SaveLogin saveLogin = new SaveLogin();
                            saveLogin.saveUserInfo(mActivity);
                        } else if(!id.equals(loginDTO.getId()) || !pw.equals(loginDTO.getPw())){
                            Snackbar.make(v, "아이디, 비밀번호를 확인하세요.", Snackbar.LENGTH_LONG).show();
                            edt_login_id.setText("");
                            edt_login_pw.setText("");
                            edt_login_id.requestFocus();
                        }

                    } else {
                        Snackbar.make(v, "아이디, 비밀번호를 확인하세요.", Snackbar.LENGTH_LONG).show();
                        edt_login_id.setText(null);
                        edt_login_pw.setText(null);
                        edt_login_id.requestFocus();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } //try

            }
        });

        //네이버 로그인
        naver_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext = mActivity.getApplicationContext();
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(
                        mContext
                        ,getString(R.string.naver_client_id)
                        ,getString(R.string.naver_client_secret)
                        ,getString(R.string.naver_client_name)
                        //,OAUTH_CALLBACK_INTENT
                        // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
                );

                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData","accessToken : "+ accessToken);
                            Log.i("LoginData","refreshToken : "+ refreshToken);
                            Log.i("LoginData","expiresAt : "+ expiresAt);
                            Log.i("LoginData","tokenType : "+ tokenType);
                        } else {
                            String errorCode = mOAuthLoginModule
                                    .getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            Toast.makeText(mContext, "errorCode:" + errorCode
                                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                        }
                    };
                };
                mOAuthLoginModule.startOauthLoginActivity(getActivity(), mOAuthLoginHandler);
            }
        });

        //카카오로 로그인하기

        kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().loginWithKakaoTalk(getActivity(),(oAuthToken, error) -> {
                    if (error != null) {
                        Log.e(TAG, "로그인 실패", error);
                    } else if (oAuthToken != null) {
                        Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());

                        UserApiClient.getInstance().me((user, meError) -> {
                            if (meError != null) {
                                Log.e(TAG, "사용자 정보 요청 실패", meError);
                            } else {
                                System.out.println("로그인 완료");
                                Snackbar.make(v, "로그인 되었습니다.", Snackbar.LENGTH_LONG).show();
                                mActivity.onFragmentChange(fragMainPage);
                                Log.i(TAG, user.toString());
                                {
                                    Log.i(TAG, "사용자 정보 요청 성공" +
                                            "\n회원번호: "+user.getId() +
                                            "\n이메일: "+user.getKakaoAccount().getEmail());
                                }
                                Account user1 = user.getKakaoAccount();
                                System.out.println("사용자 계정" + user1);
                            }
                            return null;
                        });
                    }
                    return null;
                });
            }
        });

        // 로그인 페이지 x 뒤로 가기 버튼 클릭시 메인 화면으로 돌아가는 이벤트
        btn_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragMainPage);
            }
        }); // btn_login_back
        return rootView;
    } // onCreateView

} // class


