package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "ST_SPACEPORT")
@Entity(name = "st_Spaceport")
@NamePattern("%s|name")
public class Spaceport extends StandardEntity {
    private static final long serialVersionUID = 7517097062192848773L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLANET_ID")
    private Planet planet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOON_ID")
    private Moon moon;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    @Embedded
    private Coordinates coordinates;

    @JoinTable(name = "ST_CARRIER_SPACEPORT_LINK",
            joinColumns = @JoinColumn(name = "SPACEPORT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARRIER_ID"))
    @ManyToMany
    private List<Carrier> carriers;

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }
}