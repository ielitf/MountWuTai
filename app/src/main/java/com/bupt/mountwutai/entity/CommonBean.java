package com.bupt.mountwutai.entity;

/**
 * Created by litf on 2017/4/19.
 */

public class CommonBean {
    private int icon;
    private String title;
    private String content;

    public CommonBean (int icon,String title,String content){
        this.icon = icon;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
