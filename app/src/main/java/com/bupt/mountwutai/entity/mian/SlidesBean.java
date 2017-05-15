package com.bupt.mountwutai.entity.mian;

/**
 * 首页banner数据
 * Created by ZRP on 2016/12/8.
 */
public class SlidesBean {

    private int imgUrl;
    private String desc;

    public SlidesBean(int imgUrl, String desc) {
        this.imgUrl = imgUrl;
        this.desc = desc;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
