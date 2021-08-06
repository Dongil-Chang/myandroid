package com.so.storage.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.so.storage.DTO.MemberUserDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.so.storage.MainActivity.loginDTO;
import static com.so.storage.common.CommonMethod.ipconfig;
import static com.so.storage.common.CommonMethod.project_path;

//import org.apache.http.client.config.RequestConfig;

//ATask 패키지에는 Spring과 통신하는 Asynk Task 들을 여기에 모두 넣음
public class LoginSelect extends AsyncTask<Void, Void, String> {

    private static final String TAG = "LoginSelect";
    String id;
    String pw;

    //Spring 연결시 Http 통신할 때 필요한 것들
    //HttpURLConnection

    HttpClient httpClient; //클라이언트 설정부분
    HttpPost httpPost; //Url 맵핑이 들어가는 곳
    HttpResponse httpResponse; //실제 요청하는 곳
    HttpEntity httpEntity; //파라미터나 기타 설정들이 들어가는 곳

    public LoginSelect(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    //실제 Spring과 연결하여 어떤 데이터 작업이 일어나는 부분
    @Override
    protected String doInBackground(Void... voids) {
        //Multipart 빌더를 생성
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //문자열 및 데이터 (조회 조건이나 insert시 필요한 데이터들을 넘길 때)
            //Android -> Spring 으로 가는 데이터
            builder.addTextBody("id", id, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("pw", pw, ContentType.create("Multipart/related","UTF-8"));

            //Url 만들기
            String postURL = ipconfig + project_path + "/and_login"; //바꿔줘야함

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            //접속할 Url 초기화
            httpPost = new HttpPost(postURL);
            //조회 조건이나 데이터 설정들 넘겨줌
            httpPost.setEntity(builder.build());

            //실제 Spring Url을 요청하는 부분
            httpResponse = httpClient.execute(httpPost);

            //값을 Spring에서 받아오는 부분
            httpEntity = httpResponse.getEntity();
            //entity 이용해서 값을 받아오고 그리고 html로 리턴된 부분을 getContent이용해서받아옴
            inputStream = httpEntity.getContent();
            Log.d(TAG, "doInBackground: "+inputStream);

            loginDTO = readMessage(inputStream);
            Log.d(TAG, "doInBackground: "+loginDTO.getEmail());

            inputStream.close();

        } catch (IOException e) {
            loginDTO = null;
            e.printStackTrace();
        } finally {
            //통신을 완료하고 값들을 전부 비움움
            if(httpEntity != null) {
                httpEntity = null;
            }
            if(httpResponse != null) {
                httpResponse = null;
            }
            if(httpPost != null) {
                httpPost = null;
            }
            if(httpClient != null) {
                httpClient = null;
            }
        }

        return "LoginSelect Complete!!!";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    private MemberUserDTO readMessage(InputStream inputStream) throws IOException {

        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, joindate, commcode, subcode;
        //int id = 0;
        String member_code = "";
        String id = "";
        String pw = "";
        String name = "";
        String email = "";
        String addr = "";
        String tel = "";
        String birth = "";
        String naver_login = "";
        String kakao_login = "";
        String commcode = "";
        String subcode = "";

        //json 구조에서 다음 정보가 있는지 체크하면서 while 문을 통해 무한반복
        //어레이리스트일 경우 json {{beginArray
        //reader.beginArray();
        //dto 나 map 구조 그대로를 json으로 만든 경우는 beginObject
        reader.beginObject(); //json 리더 열어줌
        while (reader.hasNext()) {
            String tempStr = reader.nextName();

            if(tempStr.equals("member_code")){
                member_code = reader.nextString();
            } else if(tempStr.equals("id")) {
                id = reader.nextString();
            } else if(tempStr.equals("pw")) {
                pw = reader.nextString();
            } else if(tempStr.equals("name")) {
                name = reader.nextString();
            } else if(tempStr.equals("email")) {
                email = reader.nextString();
            } else if(tempStr.equals("addr")) {
                addr = reader.nextString();
            } else if(tempStr.equals("tel")) {
                tel = reader.nextString();
            } else if(tempStr.equals("birth")) {
                birth = reader.nextString();
            } else if(tempStr.equals("naver_login")) {
                naver_login = reader.nextString();
            } else if(tempStr.equals("kakao_login")) {
                kakao_login = reader.nextString();
            } else if(tempStr.equals("commcode")) {
                commcode = reader.nextString();
            } else if(tempStr.equals("subcode")) {
                subcode = reader.nextString();
            } else {
                reader.skipValue();
                //reader 에 들어있는 값을 skip
            }
        }

        //reader.endArray();
        reader.endObject(); //json 리더 닫아줌

        Log.d(TAG, "readMessage: " + member_code + ", " +id + ", " +pw + ", " +name + ", " +email + ", " +addr + ", " +tel + ", " +birth);
        return new MemberUserDTO(member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, commcode, subcode);
        //리턴할 때 초기값 줘야 함. null 처리
    }
}
