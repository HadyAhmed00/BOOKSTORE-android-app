package com.example.travel.ShowList;

public class ShowListModel {
    private String book_id;
    private String name;
    private String outher_name;
    private String img_url;
    private String discretion;

    public ShowListModel() {
    }

    public ShowListModel(String book_id, String name, String outher_name, String img_url, String discrption) {
        this.book_id = book_id;
        this.name = name;
        this.outher_name = outher_name;
        this.img_url = img_url;
        this.discretion = discrption;
    }

    public ShowListModel(String name, String outher_name, String img_url) {
        this.name = name;

        this.outher_name = outher_name;
        this.img_url = img_url;

    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getDiscretion() {
        return discretion;
    }

    public void setDiscretion(String discretion) {
        this.discretion = discretion;
    }

    public String getName() {
        return name;
    }


    public String getOuther_name() {
        return outher_name;
    }

    public String getImg_url() {
        return img_url;
    }


}
