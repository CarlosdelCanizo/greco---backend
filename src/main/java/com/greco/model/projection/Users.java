//USER_EXIT_METADATA#JAVA_SPRINGBOOT#846767211#10351
package com.greco.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.io.Serializable;

public class Users implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
	private Long userId;
	private String email;
    private String username;
    private Boolean isPreviouslyLogged;
    @JsonIgnore
    private Boolean firstTime;
    //endregion

    //region Getters & Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsPreviouslyLogged() {
        return isPreviouslyLogged;
    }

    public void setIsPreviouslyLogged(Boolean isPreviouslyLogged) {
        this.isPreviouslyLogged = isPreviouslyLogged;
    }

    public Boolean getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Boolean firstTime) {
        this.firstTime = firstTime;
    }

//endregion
}




