package com.greco.model.projection;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable, IProjectable{

    private Long id ;

    private String text;

    private Timestamp creationDate;


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

}
