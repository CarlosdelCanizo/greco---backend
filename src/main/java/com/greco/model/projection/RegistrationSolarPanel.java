package com.greco.model.projection;

import java.io.Serializable;
import java.sql.Timestamp;

public class RegistrationSolarPanel implements Serializable, IProjectable{
    private Long id ;
    private Timestamp registrationDate;
    private String photographOfInstallation;
    private SolarPanel solarPanel;
    private Users owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhotographOfInstallation() {
        return photographOfInstallation;
    }

    public void setPhotographOfInstallation(String photographOfInstallation) {
        this.photographOfInstallation = photographOfInstallation;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}
