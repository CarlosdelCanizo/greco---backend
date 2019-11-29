package com.greco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greco.model.projection.IProjectable;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "level")
public class Level implements Serializable, IProjectable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_level", sequenceName="SEQ_level", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_level")
    @Column(name = "id" )
    private Long id;

    @Column(name = "name", scale = 0, length = 255, nullable = false )
    private String name;

    @Column(name = "condition_to_achieve_it", scale = 0, length = 5000, nullable = false )
    private String conditionToAchieveIt;

    @Column(name = "order_in_challenge", nullable = false)
    private Long orderInChallenge;

    @Column(name = "image", scale = 0, length = 255, nullable = false )
    private String image;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge")
    private Challenge challenge ;

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

    public String getConditionToAchieveIt() {
        return conditionToAchieveIt;
    }

    public void setConditionToAchieveIt(String conditionToAchieveIt) {
        this.conditionToAchieveIt = conditionToAchieveIt;
    }

    public Long getOrderInChallenge() {
        return orderInChallenge;
    }

    public void setOrderInChallenge(Long orderInChallenge) {
        this.orderInChallenge = orderInChallenge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
