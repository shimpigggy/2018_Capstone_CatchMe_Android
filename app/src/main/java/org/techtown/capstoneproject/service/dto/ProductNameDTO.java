package org.techtown.capstoneproject.service.dto;

import java.io.Serializable;

/**
 * Created by shimp on 2018-06-06.
 */

public class ProductNameDTO implements Serializable{
    int num;
    String productName;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
