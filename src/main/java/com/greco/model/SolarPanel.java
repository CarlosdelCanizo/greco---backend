package com.greco.model;
import com.greco.model.projection.IProjectable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "solar_panel")
public class SolarPanel implements Serializable, IProjectable
{
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_solar_panel", sequenceName="SEQ_solar_panel", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_solar_panel")
    @Column(name = "id" )
    private Long id ;

    @Column(name = "lat", scale = 0, length = 255, nullable = true )
    private String lat;

    @Column(name = "lon", scale = 0, length = 255, nullable = true )
    private String lon;

    @Column(name = "municipality", scale = 0, length = 255, nullable = true )
    private String municipality;

    @Column(name = "postcode", scale = 0, length = 255, nullable = true )
    private String postcode;

    @Column(name = "orientation", scale = 0, length = 255, nullable = true )
    private String orientation;

    @Column(name = "inclination", scale = 0, length = 255, nullable = true )
    private String inclination;

    @Column(name = "electrical_capacity", scale = 0, length = 100, nullable = true )
    private Double electricalCapacity;

    @Column(name = "technology_used", scale = 0, length = 255, nullable = true )
    private String technologyUsed;

    @Column(name = "commissioning_date", nullable = true )
    private Date commissioningDate;

    @Column(name = "inverter_capacity", scale = 0, length = 255, nullable = true )
    private String inverterCapacity;

    @Column(name = "tracking", scale = 0, length = 255, nullable = true )
    private String tracking;

    @Column(name = "generation_data", scale = 0, length = 255, nullable = true )
    private String generationData;

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
}
