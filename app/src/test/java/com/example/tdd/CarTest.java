package com.example.tdd;

import static android.icu.text.Transliterator.FORWARD;

import android.graphics.Point;
import androidx.appcompat.widget.ForwardingListener;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhengwei on 2020/12/17.
 */
public class CarTest {

    //1.位置合法
    //2.运动
    //3.转向
    //4.指令发送

    //A ... B
    //C ... D

    //指令 input output
    @Test
    public void testPositionValid1(){
        PointBean pointA = new PointBean(-10, 10);
        PointBean pointB = new PointBean(10, 10);
        PointBean pointC = new PointBean(-10, -10);
        PointBean pointD = new PointBean(10, -10);

        AreaBean areaBean = new AreaBean(pointA, pointB, pointC, pointD);

        PointBean currentPoint = new PointBean(10,1);
        Assert.assertTrue(areaBean.contains(currentPoint));
    }

    @Test
    public void testPositionValid2(){
        PointBean pointA = new PointBean(-10, 10);
        PointBean pointB = new PointBean(10, 10);
        PointBean pointC = new PointBean(-10, -10);
        PointBean pointD = new PointBean(10, -10);

        AreaBean areaBean = new AreaBean(pointA, pointB, pointC, pointD);

        PointBean currentPoint = new PointBean(100,1);
        Assert.assertFalse(areaBean.contains(currentPoint));
    }

    //火星车
    @Test
    public void testMoveForward(){
        PointBean currentPoint = new PointBean(0, 0);
        DirectionBean direction = new DirectionBean(FORWARD);
    }

}
