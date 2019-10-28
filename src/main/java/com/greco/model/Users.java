package com.greco.model;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;
import com.greco.model.projection.IProjectable;
import javax.persistence.Id;

@Entity
@Table(name = "users")
public class Users implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;

    //region Properties
        	@ManyToOne(fetch = FetchType.LAZY)
        	@JoinColumn(name = "rol_id" )
	private Rol rolId ;

        	@Id
            	@SequenceGenerator(name="SEQ_users", sequenceName="SEQ_users", allocationSize= 1)
            	@GeneratedValue(generator = "SEQ_users")
        	@Column(name = "user_id" )
	private Long userId ;

            	@Column(name = "username", scale = 0, length = 30, nullable = false )
	private String username ;

            	@Column(name = "password", scale = 0, length = 255, nullable = false )
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




