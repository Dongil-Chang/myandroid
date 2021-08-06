package com.so.storage.DTO;

public class BoxDTO {
    public int box_img;
    public String box_name, box_info;
    public int color_img, click_img;

    public BoxDTO(int box_img, String box_name, String box_info, int color_img, int click_img) {
        this.box_img = box_img;
        this.box_name = box_name;
        this.box_info = box_info;
        this.color_img = color_img;
        this.click_img = click_img;
    }

    public int getBox_img() {
        return box_img;
    }

    public void setBox_img(int box_img) {
        this.box_img = box_img;
    }

    public String getBox_name() {
        return box_name;
    }

    public void setBox_name(String box_name) {
        this.box_name = box_name;
    }

    public String getBox_info() {
        return box_info;
    }

    public void setBox_info(String box_info) {
        this.box_info = box_info;
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
