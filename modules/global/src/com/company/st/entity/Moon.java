package com.company.st.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;

@Table(name = "ST_MOON")
@Entity(name = "st_Moon")
@NamePattern("%s|name")
public class Moon extends AstronimicBody {
    private static final long serialVersionUID = 6317174489669302873L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLANET_ID")
    private Planet planet;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATMOSPHERE_ID")
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}