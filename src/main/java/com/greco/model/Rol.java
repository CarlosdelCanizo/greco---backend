package com.greco.model;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;
import com.greco.model.projection.IProjectable;
import javax.persistence.Id;

@Entity
@Table(name = "rol")
public class Rol implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
            	@Column(name = "name", scale = 0, length = 20, nullable = false )
	private String name ;

        	@Id
            	@SequenceGenerator(name="SEQ_rol", sequenceName="SEQ_rol", allocationSize= 1)
            	@GeneratedValue(generator = "SEQ_rol")
        	@Column(name = "rol_id" )
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




