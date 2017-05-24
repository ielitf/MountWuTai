package com.bupt.mountwutai.entity.mian;

/**
 *
 */

public class BusinessTypeListBean {

    private String date;
    private String posterUrl;
    private String id;
    private String title;
    private String content;
    private String isHot;
    private String address;
    private String phone;
    private String fee;
    private String modName;
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

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    @Override
    public String toString() {
        return "BusinessTypeListBean{" +
                "date='" + date + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isHot='" + isHot + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fee='" + fee + '\'' +
                ", modName='" + modName + '\'' +
                '}';
    }
}
