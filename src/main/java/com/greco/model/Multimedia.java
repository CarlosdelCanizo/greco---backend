package com.greco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greco.model.projection.IProjectable;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "multimedia")
public class Multimedia implements Serializable, IProjectable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_multimedia", sequenceName="SEQ_multimedia", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_multimedia")
    @Column(name = "id" )
    private Long id ;

    @Column(name = "name", scale = 0, length = 255, nullable = true )
    private String name;

    @Column(name = "description", scale = 0, length = 255, nullable = true )
    private String description;

    @Column(name = "creation_date", nullable = true )
    private Timestamp creationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solar_panel")
    private SolarPanel solarPanel ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }
}
