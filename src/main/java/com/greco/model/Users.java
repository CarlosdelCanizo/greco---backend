package com.greco.model;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.sql.Timestamp;
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
    @Id
    @SequenceGenerator(name="SEQ_users", sequenceName="SEQ_users", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_users")
    @Column(name = "user_id" )
	private Long userId ;

    @Column(name = "email", scale = 0, length = 255, nullable = false )
	private String email ;

    @Column(name = "password", scale = 0, length = 255, nullable = false )
	private String password ;

    @Column(name = "privacy_policy_acceptance_date", nullable = false )
    private Timestamp privacyPolicyAcceptanceDate;

    @Column(name = "uuid", scale = 0, length = 255, nullable = false )
    private String uuid;

    @Column(name = "username", scale = 0, length = 100, nullable = false )
    private String username ;

    @Column(name = "first_time")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getPrivacyPolicyAcceptanceDate() {
        return privacyPolicyAcceptanceDate;
    }

    public void setPrivacyPolicyAcceptanceDate(Timestamp privacyPolicyAcceptanceDate) {
        this.privacyPolicyAcceptanceDate = privacyPolicyAcceptanceDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Boolean firstTime) {
        this.firstTime = firstTime;
    }
//endregion
}




