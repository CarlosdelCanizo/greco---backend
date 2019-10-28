//USER_EXIT_METADATA#JAVA_SPRINGBOOT#846767211#10349
package com.greco.model.projection;

import java.util.Date;
import java.io.Serializable;

public class Rol implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
	private String name ;

	private Long rolId ;

	


    //endregion

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }


    //endregion
}




