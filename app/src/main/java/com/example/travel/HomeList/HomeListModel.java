package com.example.travel.HomeList;

public class HomeListModel {
    private String catogry;
    private int icon;

    public String getCatogry() {
        return catogry;
    }

    public void setCatogry(String catogry) {
        this.catogry = catogry;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public HomeListModel(String catogry, int icon) {
        this.catogry = catogry;
        this.icon = icon;
    }
}
