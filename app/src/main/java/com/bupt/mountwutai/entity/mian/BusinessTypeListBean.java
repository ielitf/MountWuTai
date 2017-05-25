package com.bupt.mountwutai.entity.mian;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */

public class BusinessTypeListBean implements Parcelable{

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

    protected BusinessTypeListBean(Parcel in) {
        date = in.readString();
        posterUrl = in.readString();
        id = in.readString();
        title = in.readString();
        content = in.readString();
        isHot = in.readString();
        address = in.readString();
        phone = in.readString();
        fee = in.readString();
        modName = in.readString();
        icon = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(posterUrl);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(isHot);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(fee);
        dest.writeString(modName);
        dest.writeInt(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BusinessTypeListBean> CREATOR = new Creator<BusinessTypeListBean>() {
        @Override
        public BusinessTypeListBean createFromParcel(Parcel in) {
            return new BusinessTypeListBean(in);
        }

        @Override
        public BusinessTypeListBean[] newArray(int size) {
            return new BusinessTypeListBean[size];
        }
    };

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
