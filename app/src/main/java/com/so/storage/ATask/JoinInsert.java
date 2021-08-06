package com.so.storage.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

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

//ATask 패키지에는 Spring과 통신하는 Asynk Task 들을 여기에 모두 넣음
public class JoinInsert extends AsyncTask<Void, Void, Void> {
    MemberUserDTO dto;


    //Spring 연결시 Http 통신할 때 필요한 것들
    HttpClient httpClient; //클라이언트 설정부분
    HttpPost httpPost; //Url 맵핑이 들어가는 곳
    HttpResponse httpResponse; //실제 요청하는 곳
    HttpEntity httpEntity; //파라미터나 기타 설정들이 들어가는 곳

    /*public JoinSelect(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }*/

    public JoinInsert(MemberUserDTO dto) {
        this.dto = dto;
    }


    //실제 Spring과 연결하여 어떤 데이터 작업이 일어나는 부분
    @Override
    protected Void doInBackground(Void... voids) {
        //Multipart 빌더를 생성
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, commcode, subcode;

            //문자열 및 데이터 (조회 조건이나 insert시 필요한 데이터들을 넘길 때)
            //Android -> Spring 으로 가는 데이터
            //builder.addTextBody("member_code", dto.getMember_code(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("id", dto.getId(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("pw", dto.getPw(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("name", dto.getName(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("email", dto.getEmail(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("addr", dto.getAddr(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("tel", dto.getTel(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("birth", dto.getBirth(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("naver_login", dto.getNaver_login(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("kakao_login", dto.getKakao_login(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("commcode", dto.getCommcode(), ContentType.create("Multipart/related","UTF-8"));
            //builder.addTextBody("subcode", dto.getSubcode(), ContentType.create("Multipart/related","UTF-8"));
            //  builder.addTextBody("id", dto.getId(), ContentType.create("Multipart/related","UTF-8"));

            //Url 만들기
            String postURL = ipconfig + project_path + "/and_join";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            //접속할 Url 초기화
            httpPost = new HttpPost(postURL);
            //조회 조건이나 데이터 설정들 넘겨줌
            httpPost.setEntity(builder.build());
            //httpPost.setEntity(dto);

            //실제 Spring Url을 요청하는 부분
            httpResponse = httpClient.execute(httpPost);

            //값을 Spring에서 받아오는 부분
            httpEntity = httpResponse.getEntity();
            //entity 이용해서 값을 받아오고 그리고 html로 리턴된 부분을 getContent이용해서받아옴
            inputStream = httpEntity.getContent();

            String result = readMessage(inputStream);

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

        return null;
    }

    private String readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        String result = "";
        //json 구조에서 다음 정보가 있는지 체크하면서 while 문을 통해 무한반복
        //어레이리스트일 경우 json {{beginArray
        //reader.beginArray();
        //dto 나 map 구조 그대로를 json으로 만든 경우는 beginObject
        reader.beginObject(); //json 리더 열어줌
        while (reader.hasNext()) {
            String tempStr = reader.nextName();
            if(tempStr.equals("result")){
                result = reader.nextString();
            } else {
                reader.skipValue();
                //reader 에 들어있는 값을 skip
            }
        }

        //reader.endArray();
        reader.endObject(); //json 리더 닫아줌
        return result;
        //리턴할 때 초기값 줘야 함. null 처리
    }
}
