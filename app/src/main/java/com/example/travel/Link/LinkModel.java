package com.example.travel.Link;

public class LinkModel {
    private int usr_id;
    private String book_id;

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public LinkModel(int usr_id, String book_id) {
        this.usr_id = usr_id;
        this.book_id = book_id;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public String getBook_id() {
        return book_id;
    }
}
