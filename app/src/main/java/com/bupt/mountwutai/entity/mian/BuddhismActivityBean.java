package com.bupt.mountwutai.entity.mian;

/**
 * Created by litf on 2017/5/11.
 */

public class BuddhismActivityBean {
    private int icon;
    private String title;
    private boolean isBegin;


    public BuddhismActivityBean(int icon, String title, boolean isBegin) {
        this.icon = icon;
        this.title = title;
        this.isBegin = isBegin;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBegin() {
        return isBegin;
    }

    public void setBegin(boolean begin) {
        isBegin = begin;
    }

    @Override
    public String toString() {
        return "BuddhismActivityBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", isBegin=" + isBegin +
                '}';
    }
}
