package org.techtown.capstoneproject.service.dto;

import java.io.Serializable;

/**
 * Created by hahav on 2018-05-26.
 */
public class ChemicalDTO implements Serializable{
    int num;
    String nameK;
    String nameE;
    String cas;
    String definition;
    String used;
    String dryGood;
    String dryBad;
    String oilGood;
    String oilBad;
    String sensitiveGood;
    String sensitiveBad;
    String complexBad;
    String functionFor;
    String allergy;
    String warning;
    String acne;
    String productList;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getDryGood() {
        return dryGood;
    }

    public void setDryGood(String dryGood) {
        this.dryGood = dryGood;
    }

    public String getDryBad() {
        return dryBad;
    }

    public void setDryBad(String dryBad) {
        this.dryBad = dryBad;
    }

    public String getOilGood() {
        return oilGood;
    }

    public void setOilGood(String oilGood) {
        this.oilGood = oilGood;
    }

    public String getOilBad() {
        return oilBad;
    }

    public void setOilBad(String oilBad) {
        this.oilBad = oilBad;
    }

    public String getSensitiveGood() {
        return sensitiveGood;
    }

    public void setSensitiveGood(String sensitiveGood) {
        this.sensitiveGood = sensitiveGood;
    }

    public String getSensitiveBad() {
        return sensitiveBad;
    }

    public void setSensitiveBad(String sensitiveBad) {
        this.sensitiveBad = sensitiveBad;
    }

    public String getComplexBad() {
        return complexBad;
    }

    public void setComplexBad(String complexBad) {
        this.complexBad = complexBad;
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

    public String getAcne() {
        return acne;
    }

    public void setAcne(String acne) {
        this.acne = acne;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "DTO [nameK=" + nameK + ", nameE=" + nameE + ", cas=" + cas + ", definition=" + definition + ", used="
                + used + ", dryGood=" + dryGood + ", dryBad=" + dryBad + ", oilGood=" + oilGood + ", oilBad=" + oilBad
                + ", sensitiveGood=" + sensitiveGood + ", sensitiveBad=" + sensitiveBad + ", complexBad=" + complexBad
                + ", functionFor=" + functionFor + ", allergy=" + allergy + ", warning=" + warning + ", acne=" + acne
                + ",productList=" + productList + "]";
    }

}