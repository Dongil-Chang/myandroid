package com.so.storage.DTO;

public class NoticeBoardDTO {

    private String board_code;
    private int board_num;
    private String board_write_date, board_edit_date, board_title, board_content, board_file, board_comment, commcode, subcode;
    private boolean isExpanded;

    public NoticeBoardDTO() {}

    /*public NoticeBoardDTO(String board_code, int board_num, String board_write_date, String board_edit_date, String board_title, String board_content, String board_file, String board_comment, String commcode, String subcode) {
        this.board_code = board_code;
        this.board_num = board_num;
        this.board_write_date = board_write_date;
        this.board_edit_date = board_edit_date;
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_file = board_file;
        this.board_comment = board_comment;
        this.commcode = commcode;
        this.subcode = subcode;
    }*/

    public String getBoard_code() {
        return board_code;
    }

    public void setBoard_code(String board_code) {
        this.board_code = board_code;
    }

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public String getBoard_write_date() {
        return board_write_date;
    }

    public void setBoard_write_date(String board_write_date) {
        this.board_write_date = board_write_date;
    }

    public String getBoard_edit_date() {
        return board_edit_date;
    }

    public void setBoard_edit_date(String board_edit_date) {
        this.board_edit_date = board_edit_date;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_file() {
        return board_file;
    }

    public void setBoard_file(String board_file) {
        this.board_file = board_file;
    }

    public String getBoard_comment() {
        return board_comment;
    }

    public void setBoard_comment(String board_comment) {
        this.board_comment = board_comment;
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

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
} // class