package com.greco.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
/*@Entity
@Table(name = "solarPanel")*/
public class SolarPanel {
    private static final long serialVersionUID = 1L;
    private String lat;
    private String lon;
    private String municipality;
    private String postcode;
    private String orientation;
    private String inclination;
    private Double electricalCapacity;
    private String technologyUsed;
    private Date commissioningDate;
    private String inverterCapacity;
    private String tracking;
    private String generationData;
    private String photographOfInstallation;

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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getInclination() {
        return inclination;
    }

    public void setInclination(String inclination) {
        this.inclination = inclination;
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

    public String getPhotographOfInstallation() {
        return photographOfInstallation;
    }

    public void setPhotographOfInstallation(String photographOfInstallation) {
        this.photographOfInstallation = photographOfInstallation;
    }
}
