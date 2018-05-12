package org.techtown.capstoneproject;

/**
 * Created by ShimPiggy on 2018-05-12.
 */


public class ListviewItem {
    private int num;
    private String name;

    public ListviewItem(int _num, String _name) {
        num = _num;
        name = _name;
    }

    public String getNum() {
        return num+"";
    }

    public String getName() {
        return name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }
}
