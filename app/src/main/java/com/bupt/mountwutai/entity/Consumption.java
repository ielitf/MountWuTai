package com.bupt.mountwutai.entity;

/**
 * Created by linlongxin on 2016/1/26.
 */
public class Consumption {

    private int livepic;
    private String livetext;

    public Consumption(int livepic, String livetext) {
        this.livepic = livepic;
        this.livetext = livetext;
    }

    public int getLivepic() {
        return livepic;
    }

    public void setLivepic(int livepic) {
        this.livepic = livepic;
    }

    public String getLivetext() {
        return livetext;
    }

    public void setLivetext(String livetext) {
        this.livetext = livetext;
    }
}
