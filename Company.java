package com.example.itjobportal.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String logoUrl;
    private String location;
    private BigDecimal geolocationLatitude;
    private BigDecimal geolocationLongitude;


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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getGeolocationLatitude() {
        return geolocationLatitude;
    }

    public void setGeolocationLatitude(BigDecimal geolocationLatitude) {
        this.geolocationLatitude = geolocationLatitude;
    }

    public BigDecimal getGeolocationLongitude() {
        return geolocationLongitude;
    }

    public void setGeolocationLongitude(BigDecimal geolocationLongitude) {
        this.geolocationLongitude = geolocationLongitude;
    }


}
