package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Table(name = "ST_COORDINATES")
@Entity(name = "st_Coordinates")
@NamePattern("%s %s|latitude,longtitude")
public class Coordinates extends StandardEntity {
    private static final long serialVersionUID = -2327920420596891354L;

    @NumberFormat(pattern = "###.000000", decimalSeparator = ",")
    @NotNull
    @Column(name = "LATITUDE", nullable = false)
    @DecimalMin("-90")
    @DecimalMax("90")
    @Digits(integer = 3, fraction = 6)
    private Double latitude;

    @NumberFormat(pattern = "###,000000°", decimalSeparator = ",")
    @NotNull
    @Column(name = "LONGTITUDE", nullable = false)
    @DecimalMin("-180")
    @DecimalMax("180")
    private Double longtitude;

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}