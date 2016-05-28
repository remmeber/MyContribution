package com.example.rhg.outsourcing.bean;
/**
 *desc:主页店铺推荐网络请求数据模型
 *author：remember
 *time：2016/5/28 16:38
 *email：1013773046@qq.com
 */
public class RecommendListUrlBean {
    private String ID;
    private  String Name;
    private String Src;
    private String Distance;
    private String Jump;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String src) {
        Src = src;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getJump() {
        return Jump;
    }

    public void setJump(String jump) {
        Jump = jump;
    }
}
