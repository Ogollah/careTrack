package com.stephen.careTrack.DTO;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class PersonDTO
{
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotBlank
    private String sex;
    private Date birth_date;
    @NotBlank
    private String phone;
    private int IDNumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }


}
