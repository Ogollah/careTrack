package com.stephen.careTrack.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The `Visit` class represents a visit record in the database.
 * It stores information about a patient's visit, including visit date, measurements, and health status.
 */
@Entity
@Table(name = "visits")
public class Visit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date visit_date;
    @NotNull
    private double height;
    @NotNull
    private double weight;
    @NotNull
    private double bmi;
    private String general_health;
    private boolean on_diet;
    private boolean taking_drugs;
    private String comments;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User registeredBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visitDate) {
        this.visit_date = visitDate;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
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
}
