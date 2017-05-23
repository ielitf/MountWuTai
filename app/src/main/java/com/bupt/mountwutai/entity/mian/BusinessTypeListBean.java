package com.bupt.mountwutai.entity.mian;

/**
 *
 */

public class BusinessTypeListBean {

    /**
     * date : 2016-12-06 14:24:11
     * posterUrl : static/titleImage/1482802802457-26475.jpg
     * id : 91933
     * title : 电视互动及置换业务
     * isHot : 0
     */

    private String date;
    private String posterUrl;
    private String id;
    private String title;
    private String isHot;
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

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        return "BusinessTypeListBean{" +
                "date='" + date + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isHot='" + isHot + '\'' +
                ", icon=" + icon +
                '}';
    }
}
