package com.stephen.careTrack.DTO;

import javax.validation.constraints.NotBlank;

public class UserDTO extends PersonDTO
{
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String username;
    @NotBlank
    private String password;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
