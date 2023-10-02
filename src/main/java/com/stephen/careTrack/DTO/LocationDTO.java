package com.stephen.careTrack.DTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LocationDTO implements Serializable
{
    private static final long serialVersion = 1L;
    @NotBlank
    private String longitude;
    @NotBlank
    private String latitude;
    @NotBlank
    private String name;
    @NotBlank
    private String mfl;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMfl() {
        return mfl;
    }

    public void setMfl(String mfl) {
        this.mfl = mfl;
    }
}
