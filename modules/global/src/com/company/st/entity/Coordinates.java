package com.company.st.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.EmbeddableEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@MetaClass(name = "st_Coordinates")
@Embeddable
public class Coordinates extends EmbeddableEntity {
    private static final long serialVersionUID = -2327920420596891354L;

    @NumberFormat(pattern = "##0.000000", decimalSeparator = ",")
    @Column(name = "LATITUDE", nullable = false)
    @DecimalMin("-90")
    @DecimalMax("90")
    @Digits(integer = 3, fraction = 6)
    private Double latitude;

    @NumberFormat(pattern = "##0.000000", decimalSeparator = ",")
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