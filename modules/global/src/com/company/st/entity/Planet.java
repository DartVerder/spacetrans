package com.company.st.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "ST_PLANET")
@Entity(name = "st_Planet")
@NamePattern("%s|name")
public class Planet extends AstronimicBody {
    private static final long serialVersionUID = -2778544479570002416L;

    @Column(name = "SEMI_MAJOR_AXIS", nullable = false)
    @NotNull
    private Double semiMajorAxis;

    @Column(name = "ORBITAL_PERIOD", nullable = false)
    @NotNull
    private Double orbitalPeriod;

    @Column(name = "ROTATION_PERIOD", nullable = false)
    @NotNull
    private Double rotationPeriod;

    @JoinColumn(name = "ATMOSPHERE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    private Atmosphere atmosphere;

    @Column(name = "RINGS", nullable = false)
    @NotNull
    private Boolean rings = false;

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Boolean getRings() {
        return rings;
    }

    public void setRings(Boolean rings) {
        this.rings = rings;
    }

    public void setRings(String rings) {
        this.rings = rings.contains("y");
    }

    public Double getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(Double rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod =  Double.parseDouble(rotationPeriod);
    }

    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(Double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = Double.parseDouble(orbitalPeriod);
    }

    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(Double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public void setMass(String mass) {
        super.setMass(Double.parseDouble(mass));
    }
    public void setSemiMajorAxis(String semiMajorAxis) {
        this.semiMajorAxis = Double.parseDouble(semiMajorAxis);
    }


}