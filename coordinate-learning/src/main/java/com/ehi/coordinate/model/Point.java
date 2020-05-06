package com.ehi.coordinate.model;

import java.io.Serializable;

/**
 * @author Mr_Wang
 * @PackageName:com.ehi.coordinate.model
 * @ClassName:Point
 * @Desciption:
 * @date 2020/4/7 22:53
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 3584864663880053897L;

    /**
     * 经度
     */
    private double lng;

    /**
     * 纬度
     */
    private double lat;

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
