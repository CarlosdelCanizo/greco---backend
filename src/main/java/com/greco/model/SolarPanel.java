package com.greco.model;

import com.greco.model.projection.IProjectable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "solar_panel")
public class SolarPanel implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;
    // Mandatory fields
    @Id
    @SequenceGenerator(name="SEQ_solar_panel", sequenceName="SEQ_solar_panel", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_solar_panel")
    @Column(name = "id" )
    private Long id ;

    @Column(name = "lat", nullable = false )
    private Double  lat;

    @Column(name = "lon", nullable = false )
    private Double lon;

    // Semi mandatory fields
    @Column(name = "panel_tracking_orientation")
    private Boolean panelTrackingOrientation;

    @Column(name = "panel_tracking_inclination")
    private Boolean panelTrackingInclination;

    @Column(name = "orientation", nullable = true )
    private Double orientation;

    @Column(name = "inclination", nullable = true )
    private Double inclination;

    // Not mandatory fields
    @Column(name = "surface", nullable = true )
    private Double surface;

    @Column(name = "inverter_capacity", scale = 0, length = 255, nullable = true )
    private String inverterCapacity;
    // todo eliminar comment field
//    @Column(name = "comment", scale = 0, length = 2000, nullable = true )
//    private String comment;

    @Column(name = "observation", scale = 0, length = 2000, nullable = true )
    private String observation;

    @Column(name = "battery")
    private Boolean battery;

    @Column(name = "battery_description", scale = 0, length = 255, nullable = true )
    private String batteryDescription;

    @Column(name = "installation_name", scale = 0, length = 255, nullable = true )
    private String installationName;

    @Column(name = "installation_property", scale = 0, length = 255, nullable = true )
    private String installationProperty;

    @Column(name = "installation_type", scale = 0, length = 255, nullable = true )
    private String installationType;

    @Column(name = "municipality", scale = 0, length = 255, nullable = true )
    private String municipality;

    @Column(name = "postcode", scale = 0, length = 255, nullable = true )
    private String postcode;

    @Column(name = "electrical_capacity", scale = 0, length = 100, nullable = true )
    private Double electricalCapacity;

    @Column(name = "technology_used", scale = 0, length = 255, nullable = true )
    private String technologyUsed;

    @Column(name = "commissioning_date", nullable = true )
    private Date commissioningDate;

    @Column(name = "tracking", scale = 0, length = 255, nullable = true )
    private String tracking;

    @Column(name = "generation_data", scale = 0, length = 255, nullable = true )
    private String generationData;

    @OneToMany(mappedBy = "solarPanel")
    private List<Multimedia> multimedia = new ArrayList<>();

    @OneToMany(mappedBy = "solarPanel")
    private List<Comment> comment = new ArrayList<>();

    @OneToOne(mappedBy = "solarPanel", cascade = CascadeType.ALL)
    private RegistrationSolarPanel registrationSolarPanel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Double getOrientation() {
        return orientation;
    }

    public void setOrientation(Double orientation) {
        this.orientation = orientation;
    }

    public Double getInclination() {
        return inclination;
    }

    public void setInclination(Double inclination) {
        this.inclination = inclination;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public String getInverterCapacity() {
        return inverterCapacity;
    }

    public void setInverterCapacity(String inverterCapacity) {
        this.inverterCapacity = inverterCapacity;
    }
    // todo eliminar
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getElectricalCapacity() {
        return electricalCapacity;
    }

    public void setElectricalCapacity(Double electricalCapacity) {
        this.electricalCapacity = electricalCapacity;
    }

    public String getTechnologyUsed() {
        return technologyUsed;
    }

    public void setTechnologyUsed(String technologyUsed) {
        this.technologyUsed = technologyUsed;
    }

    public Date getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(Date commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getGenerationData() {
        return generationData;
    }

    public void setGenerationData(String generationData) {
        this.generationData = generationData;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public RegistrationSolarPanel getRegistrationSolarPanel() {
        return registrationSolarPanel;
    }

    public void setRegistrationSolarPanel(RegistrationSolarPanel registrationSolarPanel) {
        this.registrationSolarPanel = registrationSolarPanel;
    }

    public Boolean getPanelTrackingOrientation() {
        return panelTrackingOrientation;
    }

    public void setPanelTrackingOrientation(Boolean panelTrackingOrientation) {
        this.panelTrackingOrientation = panelTrackingOrientation;
    }

    public Boolean getPanelTrackingInclination() {
        return panelTrackingInclination;
    }

    public void setPanelTrackingInclination(Boolean panelTrackingInclination) {
        this.panelTrackingInclination = panelTrackingInclination;
    }

    public Boolean getBattery() {
        return battery;
    }

    public void setBattery(Boolean battery) {
        this.battery = battery;
    }

    public String getBatteryDescription() {
        return batteryDescription;
    }

    public void setBatteryDescription(String batteryDescription) {
        this.batteryDescription = batteryDescription;
    }

    public String getInstallationName() {
        return installationName;
    }

    public void setInstallationName(String installationName) {
        this.installationName = installationName;
    }

    public String getInstallationProperty() {
        return installationProperty;
    }

    public void setInstallationProperty(String installationProperty) {
        this.installationProperty = installationProperty;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }
}
