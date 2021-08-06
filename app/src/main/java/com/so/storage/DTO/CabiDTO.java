package com.so.storage.DTO;

public class CabiDTO {
    public int cabi_img;
    public String cabi_name, cabi_info;
    public int color_img, click_img;


    public CabiDTO(int cabi_img, String cabi_name, String cabi_info, int color_img, int click_img) {
        this.cabi_img = cabi_img;
        this.cabi_name = cabi_name;
        this.cabi_info = cabi_info;
        this.color_img = color_img;
        this.click_img = click_img;
    }

    public int getCabi_img() {
        return cabi_img;
    }

    public void setCabi_img(int cabi_img) {
        this.cabi_img = cabi_img;
    }

    public String getCabi_name() {
        return cabi_name;
    }

    public void setCabi_name(String cabi_name) {
        this.cabi_name = cabi_name;
    }

    public String getCabi_info() {
        return cabi_info;
    }

    public void setCabi_info(String cabi_info) {
        this.cabi_info = cabi_info;
    }

    public int getColor_img() {
        return color_img;
    }

    public void setColor_img(int color_img) {
        this.color_img = color_img;
    }

    public int getClick_img() {
        return click_img;
    }

    public void setClick_img(int click_img) {
        this.click_img = click_img;
    }
}
