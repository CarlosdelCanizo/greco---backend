//USER_EXIT_METADATA#JAVA_SPRINGBOOT#846767211#10351
package com.greco.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.io.Serializable;

public class Users implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
	private Long userId ;

	private String email ;

    @JsonIgnore
	private String password ;

    @JsonIgnore
    private Rol rolId ;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }
    //endregion
}




