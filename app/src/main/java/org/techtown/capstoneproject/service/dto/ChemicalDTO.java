package org.techtown.capstoneproject.service.dto;

import java.io.Serializable;

/**
 * Created by hahav on 2018-05-26.
 */

public class ChemicalDTO implements Serializable {
    String id;
    String nameK;
    String nameE;
    String cas;
    String definition;
    String used;
    String goodFor;
    String badFor;
    String functionFor;
    String allergy;
    String warning;
    String productList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameK() {
        return nameK;
    }

    public void setNameK(String nameK) {
        this.nameK = nameK;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getGoodFor() {
        return goodFor;
    }

    public void setGoodFor(String goodFor) {
        this.goodFor = goodFor;
    }

    public String getBadFor() {
        return badFor;
    }

    public void setBadFor(String badFor) {
        this.badFor = badFor;
    }

    public String getFunctionFor() {
        return functionFor;
    }

    public void setFunctionFor(String functionFor) {
        this.functionFor = functionFor;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }


    @Override
    public String toString() {
        return getId() + getNameE() + getNameK() + getAllergy() + getBadFor() + getCas();
    }
}
