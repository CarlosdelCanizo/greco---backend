//USER_EXIT_METADATA#JAVA_SPRINGBOOT#846767211#10351
package com.greco.model.projection;

import java.util.Date;
import java.io.Serializable;

public class Users implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
	private Rol rolId ;

	private Long userId ;

	private String username ;

	private String password ;


    //endregion

    //region Getters & Setters
    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    //endregion
}




