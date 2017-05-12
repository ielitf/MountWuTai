package com.bupt.mountwutai.entity.classification;

import com.bupt.mountwutai.entity.mian.PoliticsBean;

import java.util.List;

/**
 * Created by Wyf on 2017/5/12.
 */

public class ClassificationBean {

    private String title;
    private List<PoliticsBean> beanList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PoliticsBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<PoliticsBean> beanList) {
        this.beanList = beanList;
    }

    @Override
    public String toString() {
        return "ClassificationBean{" +
                "title='" + title + '\'' +
                ", beanList=" + beanList +
                '}';
    }
}
