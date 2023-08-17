package com.zty.xiaomiadmin.server.Entity.diagram;

public class ChinaMap {
    // 中国地图的省
    private String place;
    // 每个省的访问量
    private Integer pageView;

    public ChinaMap() {
    }
    public ChinaMap(String place, Integer pageView) {
        this.place = place;
        this.pageView = pageView;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    @Override
    public String toString() {
        return "ChinaMap{" +
                "place='" + place + '\'' +
                ", pageView=" + pageView +
                '}';
    }
}
