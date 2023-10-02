package com.stephen.careTrack.DTO;

import java.util.Date;

public class VisitDTO {

    private static final long serialVersionUID = 1L;
    private Date visit_date;
    private double height;
    private double weight;
    private double bmi;
    private String general_health;
    private boolean on_diet;
    private boolean taking_drugs;
    private String comments;
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGeneral_health() {
        return general_health;
    }

    public void setGeneral_health(String general_health) {
        this.general_health = general_health;
    }

    public boolean isOn_diet() {
        return on_diet;
    }

    public void setOn_diet(boolean on_diet) {
        this.on_diet = on_diet;
    }

    public boolean isTaking_drugs() {
        return taking_drugs;
    }

    public void setTaking_drugs(boolean taking_drugs) {
        this.taking_drugs = taking_drugs;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
