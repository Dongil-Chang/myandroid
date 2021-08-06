package com.so.storage.DTO;

public class MemberUserDTO {

    String member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, commcode, subcode;

    public MemberUserDTO(){}

    public MemberUserDTO(String id, String pw, String name, String email, String tel) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.tel = tel;
    }

    public MemberUserDTO(String member_code, String id, String pw, String name, String email, String addr, String tel, String birth, String naver_login, String kakao_login, String commcode, String subcode) {
        super();
        this.member_code = member_code;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.addr = addr;
        this.tel = tel;
        this.birth = birth;
        this.naver_login = naver_login;
        this.kakao_login = kakao_login;
        this.commcode = commcode;
        this.subcode = subcode;
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNaver_login() {
        return naver_login;
    }

    public void setNaver_login(String naver_login) {
        this.naver_login = naver_login;
    }

    public String getKakao_login() {
        return kakao_login;
    }

    public void setKakao_login(String kakao_login) {
        this.kakao_login = kakao_login;
    }

    public String getCommcode() {
        return commcode;
    }

    public void setCommcode(String commcode) {
        this.commcode = commcode;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }
} // class
