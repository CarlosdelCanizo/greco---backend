package com.greco.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "registration_solar_panel")
public class RegistrationSolarPanel {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_registration_solar_panel", sequenceName="SEQ_registration_solar_panel", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_registration_solar_panel")
    @Column(name = "id" )
    private Long id ;

    @Column(name = "registration_date", nullable = true )
    private Timestamp registrationDate;

    private String photographOfInstallation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "solarPanel")
    private SolarPanel solarPanel;

    @ManyToOne
    @JoinColumn(name = "owner" )
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
