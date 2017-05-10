package com.bupt.mountwutai.entity.mian;

/**
 * 首页banner数据
 * Created by ZRP on 2016/12/8.
 */
public class SlidesEntity {

    private String imgUrl;
    private String desc;

    public SlidesEntity(String imgUrl, String desc) {
        this.imgUrl = imgUrl;
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
