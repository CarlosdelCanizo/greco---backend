package com.greco.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SolarPanel implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;
    private Long id ;
    private String lat;
    private String lon;
    private String municipality;
    @JsonIgnore
    private String postcode;
    private Double orientation;
    private Double inclination;
    private Double surface;
    private Double electricalCapacity;
    private String technologyUsed;
    private Date commissioningDate;
    private String inverterCapacity;
    private String generationData;
    private String photographOfInstallation;
    private List<Multimedia> multimedia;
    private Boolean panelTrackingOrientation;
    private Boolean panelTrackingInclination;
    private String observation;
    private Boolean battery;
    private String batteryDescription;
    private String installationName;
    private String installationProperty;
    private String installationType;
    private boolean isMine;
   @JsonIgnore
   private RegistrationSolarPanel registrationSolarPanel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
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

    public String getInverterCapacity() {
        return inverterCapacity;
    }

    public void setInverterCapacity(String inverterCapacity) {
        this.inverterCapacity = inverterCapacity;
    }

    public String getGenerationData() {
        return generationData;
    }

    public void setGenerationData(String generationData) {
        this.generationData = generationData;
    }

    public String getPhotographOfInstallation() {
        return photographOfInstallation;
    }

    public void setPhotographOfInstallation(String photographOfInstallation) {
        this.photographOfInstallation = photographOfInstallation;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(boolean mine) {
        isMine = mine;
    }

   public RegistrationSolarPanel getRegistrationSolarPanel() {
        return registrationSolarPanel;
    }

    public void setRegistrationSolarPanel(RegistrationSolarPanel registrationSolarPanel) {
        this.registrationSolarPanel = registrationSolarPanel;
    }
}
