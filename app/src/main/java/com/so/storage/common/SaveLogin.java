package com.so.storage.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.so.storage.MainActivity;

import static com.so.storage.MainActivity.loginDTO;


public class SaveLogin {
    //사용할 매개변수
    //로그인 정보 저장 - 모든 페이지에서 적용될 수 있어야 함
    //자동로그인 - ?
    //로그아웃
    //마이페이지 - 내창고, 정보수정

    MainActivity mActivity;

    //static String login_user = loginDTO.getId();
    //static final String USER_NAME = "username";

    private SharedPreferences getSharePreferences(Context context) {
        //context = mActivity;
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String saveUserInfo(Context context) {
        String login_id = "", login_pw = "", login_name = "", login_subcode ="";
        if(loginDTO != null) {
            login_id = loginDTO.getId();
            login_pw = loginDTO.getPw();
            login_name = loginDTO.getName();
            login_subcode = loginDTO.getSubcode(); // 추가 (08/03)
        }
        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        //editor.putString("id", login_user);
        //user_id = login_user;
        editor.putString("id", login_id);
        editor.putString("pw", login_pw);
        editor.putString("name", login_name);
        editor.putString("subcode",login_subcode); // 추가 (08/03)
        editor.apply();
        return login_id;
    }

    public String getUserId(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String user_id = pref.getString("id", "");
        return user_id;
    }

    public String getUserPw(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String user_pw = pref.getString("pw", "");
        return user_pw;
    }

    public String logout(Context context) {
        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        editor.clear();
        editor.commit();
        return "";
    }

}//class
