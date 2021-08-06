package com.so.storage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.Manager.FragMgMemberList;
import com.so.storage.common.SaveLogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "main:";
    public static MemberUserDTO loginDTO = null;

    TextView mystorage_name;
    DrawerLayout drawerLayout;
    Button btn_login;
    Button btn_logout;
    Toolbar toolbar;
    FragLogin fragLogin;
    FragJoin fragJoin;
    FragReservation fragReservation;
    FragPrntIdPwFind fragPrntIdPwFind;
    FragPrntMyPage fragPrntMyPage;
    FragPrntInfoUse fragPrntInfoUse;
    MyAccountFrag fragAccount;
    FragMainPage fragMainPage;
    FragReservationSubBox fragReservationSubBox;
    FragReservationSubCabi fragReservationSubCabi;
    FragMgMemberList fragMgMemberList;
    FragNotice fragNotice;
    int callNum;
    private View header;

    // Fragment selected = null;   fragment 전환 시 사용할 변수


    // Navigation Drawer 가 열린 상태에서 뒤로가기 버튼 클릭시 앱이 종료되는 것을 방지하고
    // 드로어가 열린 상태에서 뒤로가기 버튼 클릭시 드로어가 닫히도록 처리
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            // Toast.makeText(this, "back btn Clicked", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        } // if ~ else
    }  // onBackPressed()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragLogin = new FragLogin();
        fragJoin = new FragJoin();
        fragReservation = new FragReservation();
        fragPrntIdPwFind = new FragPrntIdPwFind();
        fragPrntMyPage = new FragPrntMyPage(callNum);
        fragPrntInfoUse = new FragPrntInfoUse();
        fragAccount = new MyAccountFrag();
        fragMainPage = new FragMainPage();
        fragReservationSubBox = new FragReservationSubBox();
        fragReservationSubCabi = new FragReservationSubCabi();
        fragMgMemberList = new FragMgMemberList();
        fragNotice = new FragNotice();
        Log.d("GET_KEYHASH",getKeyHash());

        // Main
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragMainPage).addToBackStack(null).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragMainPage).addToBackStack(null).commit();

        drawerLayout = findViewById(R.id.drawer_layout); // drawlayout

        // 다른 위치 레이아웃 생성
        //header = getLayoutInflater().inflate(R.layout.nav_header_main, null, false);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // btn_login = findViewById(R.id.btn_login);
        // btn_sign = findViewById(R.id.btn_sign);



       /* // 회원가입 버튼 클릭시
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChange(fragJoin);
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기
            }
        });*/

        //툴바의 타이틀

        setSupportActionBar(toolbar);  //툴바를 액션바로 지정
        getSupportActionBar().setDisplayShowCustomEnabled(true);    //툴바를 보여줌
        getSupportActionBar().setDisplayShowTitleEnabled(false);  //툴바의 기본제목을 없애줌

        //네비게이션 드로어 생성해서 리스너 붙이기 : res-string으로
        //정의해놓은 값을 사용할 예정

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.getHeaderView(0);
        btn_login = (Button) header.findViewById(R.id.btn_login);
        btn_logout = (Button) header.findViewById(R.id.btn_logout);

        // 로그인 버튼 클릭시
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Intent intent = new Intent( MainActivity.this ,GuideActivity.class);
              startActivity(intent);*/
                onFragmentChange(fragLogin);

                //-----------------로그인시 로그인 버튼 로그아웃으로 바꾸는 처리(안됨) -----------
                SaveLogin saveLogin = new SaveLogin();
                String saveuserinfo = saveLogin.saveUserInfo(MainActivity.this);

                if(!saveuserinfo.isEmpty()) {
                    Log.d(TAG, saveuserinfo);

                    btn_login.setVisibility(View.GONE);
                    btn_logout.setVisibility(View.VISIBLE);
                }
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기

            } // (btn_login) onClick
        });

        //로그아웃 버튼
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveLogin saveLogin = new SaveLogin();

                String logOut = saveLogin.logout(getApplicationContext());
                btn_logout.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                drawerLayout.closeDrawers();
                Log.d(TAG, logOut+"null");
            }
        });

        int userLevel  = 1;
        String loginID = "admin";
        View headerView = navigationView.getHeaderView(0);

        if (userLevel == 0){
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(false);
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(false);
        }else if(userLevel == 1){
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(true);
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(false);
        }else if(userLevel == 2){
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(true);
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(false);
        }
    }

    // Fragment 이동 메소드
    public void onFragmentChange(Fragment frag) {
        if(frag.equals(fragLogin)) {
        } else if(frag.equals(fragJoin)) {
        } else if(frag.equals(fragReservation)) {
        } else if(frag.equals(fragPrntIdPwFind)) {
        } else if(frag.equals(fragPrntMyPage)) {
        } else if(frag.equals(fragAccount)) {
        } else if (frag.equals(fragMainPage)) {
        } else if (frag.equals(fragReservationSubBox)) {
        } else if (frag.equals(fragReservationSubCabi)) {
        } else if (frag.equals(fragPrntInfoUse)) {
        } else if (frag.equals(fragMgMemberList)) {
        } else if (frag.equals(fragNotice)) {
        }
        // if ~ else if
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag ).addToBackStack(null).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, frag ).commit();
        // 공통 코드 정리 및 .addToBackStack(null) 추가 기입
    } // onFragmentChange()

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main){
            Toast.makeText(this, "메인화면 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragMainPage);
        }
        else if(id == R.id.nav_reservation) {
            Toast.makeText(this, "예약하기 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragReservation);
        }
        else if(id == R.id.nav_notice){
            Toast.makeText(this, "공지사항 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragNotice);
        }
        else if(id == R.id.nav_event){
            Toast.makeText(this, "이벤트 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragMgMemberList);
        }
        else if(id == R.id.nav_guide){
            Toast.makeText(this, "이용 안내 눌림", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(MainActivity.this , GuideActivity.class);
            startActivity(intent);*/
            onFragmentChange(fragPrntInfoUse);

        }
        else if(id == R.id.nav_mypage){
            Toast.makeText(this, "마이페이지 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragPrntMyPage);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    } // onNavigationItemSelected()

    // ----------------------- 회원가입 유효성 검사 -----------------------------------
    public void join_validityChk() {
        EditText edt_join_id, edt_join_pw, edt_join_pwchk, edt_join_name, edt_join_email, edt_join_tel;
        TextView txtv_join_id, txtv_join_pw, txtv_join_pwchk, txtv_join_name, txtv_join_email, txtv_join_tel;

        txtv_join_id = findViewById(R.id.txtv_join_id);
        txtv_join_pw = findViewById(R.id.txtv_join_pw);
        txtv_join_name = findViewById(R.id.txtv_join_name);
        txtv_join_email = findViewById(R.id.txtv_join_email);
        txtv_join_tel = findViewById(R.id.txtv_join_tel);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_email = findViewById(R.id.edt_join_email);
        edt_join_tel = findViewById(R.id.edt_join_tel);

        if (Pattern.matches("^[a-z0-9]\\w{5,12}$", edt_join_id.getText().toString()) || edt_join_id.length() == 0) {
            txtv_join_id.setText("");
            edt_join_id.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^[a-z0-9]\\w{5,12}$", edt_join_id.getText().toString())) {
            txtv_join_id.setVisibility(View.VISIBLE);
            txtv_join_id.setText("형식이 올바르지 않습니다.");
            edt_join_id.setBackgroundResource(R.drawable.red_edittext);
        } // 아이디

        if (Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_join_pw.getText().toString()) || edt_join_pw.length() == 0) {
            txtv_join_pw.setText("");
            edt_join_pw.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_join_pw.getText().toString())) {
            txtv_join_pw.setVisibility(View.VISIBLE);
            txtv_join_pw.setText("형식이 올바르지 않습니다.");
            edt_join_pw.setBackgroundResource(R.drawable.red_edittext);
        } // 비밀번호

        if (Pattern.matches("^[가-힣]{2,5}$", edt_join_name.getText().toString()) || edt_join_name.length() == 0) {
            txtv_join_name.setText("");
            edt_join_name.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^[가-힣]{2,5}$", edt_join_name.getText().toString())) {
            txtv_join_name.setVisibility(View.VISIBLE);
            txtv_join_name.setText("형식이 올바르지 않습니다.");
            edt_join_name.setBackgroundResource(R.drawable.red_edittext);
        } // 이름

        if (Patterns.EMAIL_ADDRESS.matcher(edt_join_email.getText().toString()).matches() || edt_join_email.length() == 0) {
            txtv_join_email.setText("");
            edt_join_email.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Patterns.EMAIL_ADDRESS.matcher(edt_join_email.getText().toString()).matches()) {
            txtv_join_email.setVisibility(View.VISIBLE);
            txtv_join_email.setText("형식이 올바르지 않습니다.");
            edt_join_email.setBackgroundResource(R.drawable.red_edittext);
        } // 이메일

        if (Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_join_tel.getText().toString()) || edt_join_tel.length() == 0) {
            txtv_join_tel.setText("");
            edt_join_tel.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_join_tel.getText().toString())) {
            txtv_join_tel.setVisibility(View.VISIBLE);
            txtv_join_tel.setText("형식이 올바르지 않습니다.");
            edt_join_tel.setBackgroundResource(R.drawable.red_edittext);
        } // 전화번호

    } // join_validityChk()

    //---------------------- 마이페이지:정보수정 유효성 검사 ----------------------------
    public void account_validityChk() {
        EditText edt_account_name, edt_account_pw, edt_account_email, edt_account_tel;
        TextView txtv_account_name, txtv_account_pw, txtv_account_email, txtv_account_tel;

        txtv_account_name = findViewById(R.id.txtv_account_name);
        txtv_account_pw = findViewById(R.id.txtv_account_pw);
        txtv_account_email = findViewById(R.id.txtv_account_email);
        txtv_account_tel = findViewById(R.id.txtv_account_tel);

        edt_account_name = findViewById(R.id.edt_account_name);
        edt_account_pw = findViewById(R.id.edt_account_pw);
        edt_account_email = findViewById(R.id.edt_account_email);
        edt_account_tel = findViewById(R.id.edt_account_tel);

        if (Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_account_pw.getText().toString()) || edt_account_pw.length() == 0) {
            txtv_account_pw.setText("");
            edt_account_pw.setBackgroundResource(R.drawable.edt_border);
        } else if(!Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_account_pw.getText().toString())) {
            txtv_account_pw.setVisibility(View.VISIBLE);
            txtv_account_pw.setText("형식이 올바르지 않습니다.");
            edt_account_pw.setBackgroundResource(R.drawable.red_edittext);
        } // 비밀번호

        if (Pattern.matches("^[가-힣]{2,5}$", edt_account_name.getText().toString()) || edt_account_name.length() == 0) {
            txtv_account_name.setText("");
            edt_account_name.setBackgroundResource(R.drawable.edt_border);
        } else if(!Pattern.matches("^[가-힣]{2,5}$", edt_account_name.getText().toString())) {
            txtv_account_name.setVisibility(View.VISIBLE);
            txtv_account_name.setText("형식이 올바르지 않습니다.");
            edt_account_name.setBackgroundResource(R.drawable.red_edittext);
        } // 이름

        if (Patterns.EMAIL_ADDRESS.matcher(edt_account_email.getText().toString()).matches() || edt_account_email.length() == 0) {
            txtv_account_email.setText("");
            edt_account_email.setBackgroundResource(R.drawable.edt_border);
        } else if(!Patterns.EMAIL_ADDRESS.matcher(edt_account_email.getText().toString()).matches()) {
            txtv_account_email.setVisibility(View.VISIBLE);
            txtv_account_email.setText("형식이 올바르지 않습니다.");
            edt_account_email.setBackgroundResource(R.drawable.red_edittext);
        } // 이메일

        if (Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_account_tel.getText().toString()) || edt_account_tel.length() == 0) {
            txtv_account_tel.setText("");
            edt_account_tel.setBackgroundResource(R.drawable.edt_border);
        } else if(!Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_account_tel.getText().toString())) {
            txtv_account_tel.setVisibility(View.VISIBLE);
            txtv_account_tel.setText("형식이 올바르지 않습니다.");
            edt_account_tel.setBackgroundResource(R.drawable.red_edittext);
        } // 전화번호
    }//account_validityChk()


    // Fragment 내 EditText의 외부 클릭 즉, 포커스가 바뀌었을 때 키보드를 숨기기 위한 메소드
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                } // if
            } // if
        } // if

        return super.dispatchTouchEvent(ev);
    } // dispatchTouchEvent()

    // 회원가입 - 비밀번호 확인
    public void joinPwChk(String pwchk) {
        EditText edt_join_pw;
        TextView txtv_join_pwchk;
        txtv_join_pwchk = findViewById(R.id.txtv_join_pwchk);
        edt_join_pw = findViewById(R.id.edt_join_pw);

        String pw = edt_join_pw.getText().toString();
        if(!pwchk.equals(pw)) {
            txtv_join_pwchk.setVisibility(View.VISIBLE);
            txtv_join_pwchk.setText("동일한 비밀번호를 입력하세요");
        } else {
            txtv_join_pwchk.setVisibility(View.VISIBLE);
            txtv_join_pwchk.setText("비밀번호가 확인되었습니다.");
        } // if ~ else
    } // pwChk()

    // 마이페이지:정보수정 - 비밀번호 확인
    public void accountPwChk(String pwchk) {
        EditText edt_account_pw;
        TextView txtv_account_pwchk;
        txtv_account_pwchk = findViewById(R.id.txtv_account_pwchk);
        edt_account_pw = findViewById(R.id.edt_account_pw);

        String account_pw = edt_account_pw.getText().toString();
        if(!pwchk.equals(account_pw)) {
            txtv_account_pwchk.setVisibility(View.VISIBLE);
            txtv_account_pwchk.setText("동일한 비밀번호를 입력하세요");
        } else {
            txtv_account_pwchk.setVisibility(View.VISIBLE);
            txtv_account_pwchk.setText("비밀번호가 확인되었습니다.");
        } // if ~ else
    }//accountPwChk()

    // 키해시 얻기
    public String getKeyHash(){
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            if(packageInfo == null) return null;
            for(Signature signature: packageInfo.signatures){
                try{
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                }catch (NoSuchAlgorithmException e){
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature="+signature, e);
                }
            }
        }catch(PackageManager.NameNotFoundException e){
            Log.w("getPackageInfo", "Unable to getPackageInfo");
        }
        return null;
    }

} // End of class