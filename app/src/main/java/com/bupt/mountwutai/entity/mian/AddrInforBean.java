package com.bupt.mountwutai.entity.mian;

/**
 * 营业网点实体类
 * Created by Wyf on 2017/5/23.
 */

public class AddrInforBean {


    /**
     * date : 2016-12-16 14:49:12
     * address : 大佟沟文化大厦北塔北门
     * posterUrl : static/titleImage/1482802960069-30371.jpg
     * phone : 03147035423
     * id : 93022
     * title : 大佟沟营业厅
     */

    private String date;
    private String address;
    private String posterUrl;
    private String phone;
    private String id;
    private String title;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AddrInforBean{" +
                "date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon=" + icon +
                '}';
    }
}
