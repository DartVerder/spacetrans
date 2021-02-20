package com.company.st.entity;

import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Table(name = "ST_ATMOSPHERIC_GAS")
@Entity(name = "st_AtmosphericGas")
public class AtmosphericGas extends StandardEntity {
    private static final long serialVersionUID = 7091520209826167207L;

    @JoinColumn(name = "GAS_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private Gas gas;

    @NotNull
    @Column(name = "VOLUME", nullable = false)
    @Digits(integer = 3, fraction = 2)
    @NumberFormat(pattern = "###.00", decimalSeparator = ",")
    @DecimalMin("0")
    @DecimalMax("100")
    private Double volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATMOSPHERE_ID")
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public Gas getGas() {
        return gas;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}