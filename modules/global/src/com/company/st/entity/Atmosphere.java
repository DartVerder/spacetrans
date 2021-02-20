package com.company.st.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "ST_ATMOSPHERE")
@Entity(name = "st_Atmosphere")
@NamePattern("%s|description")
public class Atmosphere extends StandardEntity {
    private static final long serialVersionUID = 1427797692713269185L;

    @Column(name = "DESCRIPTION")
    private String description;

    @Composition
    @OnDeleteInverse(DeletePolicy.DENY)
    @OneToMany(mappedBy = "atmosphere")
    private List<AtmosphericGas> gases;

    @Column(name = "PRESSURE")
    private Double pressure;

    public List<AtmosphericGas> getGases() {
        return gases;
    }

    public void setGases(List<AtmosphericGas> gases) {
        this.gases = gases;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}