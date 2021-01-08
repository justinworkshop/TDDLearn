package com.example.tdd;

/**
 * Created by zhengwei on 2020/12/17.
 */
public class AreaBean {

    private PointBean a;
    private PointBean b;
    private PointBean c;
    private PointBean d;

    public AreaBean(PointBean a, PointBean b, PointBean c, PointBean d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //A ... B
    //C ... D

    public boolean contains(PointBean currentPoint) {
        boolean xValid = (currentPoint.x >= a.x && currentPoint.x <= d.x);
        boolean yValid = (currentPoint.y <= a.y && currentPoint.y >= d.y);
        return xValid && yValid;
    }
}
