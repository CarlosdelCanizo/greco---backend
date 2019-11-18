package com.greco.model;

import com.greco.model.projection.IProjectable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment implements Serializable, IProjectable  {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_comment", sequenceName="SEQ_comment", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_comment")
    @Column(name = "id" )
    private Long id ;

    @Column(name = "text", scale = 0, length = 1000, nullable = false )
    private String text;

    @Column(name = "creation_date", nullable = false )
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "owner" , nullable = false)
    private Users owner;

    @ManyToOne
    @JoinColumn(name = "solar_panel", nullable = false)
    private SolarPanel solarPanel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }
}
