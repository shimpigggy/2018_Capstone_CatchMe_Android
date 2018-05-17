package org.techtown.capstoneproject;

import java.io.Serializable;

/**
 * Created by ShimPiggy on 2018-05-14.
 */

public class Item implements Serializable{
    private int num;
    private String name;
    private boolean yellow_b;
    private boolean pink_b;
    private boolean blue_b;

    public Item(int _num, String _name, boolean y, boolean p, boolean b) {
        num = _num;
        name = _name;
        yellow_b = y;
        pink_b = p;
        blue_b = b;
    }

    public Item(){
    }

    public Item(int _num, String _name) {
        num = _num;
        name = _name;
        yellow_b = false;
        pink_b = false;
        blue_b = false;
    }

    public void setAll(int _num, String _name) {
        this.num = _num;
        this.name = _name;
    }

    public void setBool(boolean y, boolean p, boolean b) {
        yellow_b = y;
        pink_b = p;
        blue_b = b;
    }

    public int getNum() {
        return num;
    }

    public String getStringNum(){
        return getNum()+"";
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

    public boolean isYellow_b() {
        return yellow_b;
    }

    public void setYellow_b(boolean yellow_b) {
        this.yellow_b = yellow_b;
    }

    public boolean isPink_b() {
        return pink_b;
    }

    public void setPink_b(boolean pink_b) {
        this.pink_b = pink_b;
    }

    public boolean isBlue_b() {

        return blue_b;
    }

    public void setBlue_b(boolean blue_b) {
        this.blue_b = blue_b;
    }
}
