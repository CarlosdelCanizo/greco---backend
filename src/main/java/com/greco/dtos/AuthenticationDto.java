package com.greco.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthenticationDto {
    String email;
    String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String uuid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
