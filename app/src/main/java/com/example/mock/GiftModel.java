package com.example.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengwei on 2021/5/18.
 */
public class GiftModel {

    public GiftModel() {

    }

    public List<String> getGiftList(int level) {
        if (level == 0) {
            return getNormalGiftList();
        } else {
            return getLuxuryGiftList();
        }
    }

    private List<String> getNormalGiftList() {
        ArrayList<String> gifts = new ArrayList<>();
        gifts.add("normal");
        return gifts;
    }

    private List<String> getLuxuryGiftList() {
        ArrayList<String> gifts = new ArrayList<>();
        gifts.add("luxury");
        return gifts;
    }

    public int getGiftSum() {
        System.out.println("call real method");
        return 100;
    }
}
