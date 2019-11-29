package com.greco.model;

import com.greco.model.projection.IProjectable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challenge")
public class Challenge implements Serializable, IProjectable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_challenge", sequenceName="SEQ_challenge", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_challenge")
    @Column(name = "id" )
    private Long id;

    @OneToMany(mappedBy = "challenge")
    private List<Level> level = new ArrayList<>();

    @Column(name = "name", scale = 0, length = 255, nullable = false)
    private String name;

    @Column(name = "description", scale = 0, length = 2500, nullable = false)
    private String description;

    @Column(name = "status", scale = 0, length = 255, nullable = false)
    private String status;

    @Column(name = "image", scale = 0, length = 255, nullable = false)
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Level> getLevel() {
        return level;
    }

    public void setLevel(List<Level> level) {
        this.level = level;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
