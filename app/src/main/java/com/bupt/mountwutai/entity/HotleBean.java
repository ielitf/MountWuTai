package com.bupt.mountwutai.entity;

/**
 * 酒店数据的实体类
 */

public class HotleBean extends CommonBean {

    private String address;

    public HotleBean(int icon, String title, String content, String address) {
        super(icon, title, content);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotleBean(int icon, String title, String content) {
        super(icon, title, content);
    }
}
