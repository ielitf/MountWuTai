package com.bupt.mountwutai.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/5/10.
 */

public class CustomBean {

    private String title;
    private Boolean isadd;
    private int picture;

    public CustomBean(int picture, String title, Boolean isadd) {
        this.picture = picture;
        this.title = title;
        this.isadd = isadd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public Boolean getIsadd() {
        return isadd;
    }

    public void setIsadd(Boolean isadd) {
        this.isadd = isadd;
    }

    @Override
    public String toString() {
        return "CustomBean{" +
                "title=" + title +
                ", isadd='" + isadd +
                ", picture='" + picture +
                '}';
    }
}
