package com.bupt.mountwutai.entity.mian;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/10.
 */

public class PoliticsBean implements Serializable {

    private String title;
    private int picture;

    public PoliticsBean(int picture, String title) {
        this.picture = picture;
        this.title = title;
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

    @Override
    public String toString() {
        return "CustomBean{" +
                "title=" + title +
                ", picture='" + picture +
                '}';
    }
}
