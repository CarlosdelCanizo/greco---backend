package com.greco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greco.model.projection.IProjectable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscription")
public class Subscription implements Serializable, IProjectable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SEQ_subscription", sequenceName="SEQ_subscription", allocationSize= 1)
    @GeneratedValue(generator = "SEQ_subscription")
    @Column(name = "id" )
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge")
    private Challenge challenge;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber")
    private Users subscriber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Users getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Users subscriber) {
        this.subscriber = subscriber;
    }
}
