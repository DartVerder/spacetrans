package com.company.st.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.EmbeddableEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@MetaClass(name = "st_Dimensions")
@Embeddable
public class Dimensions extends EmbeddableEntity {
    private static final long serialVersionUID = 4270996463483093852L;

    @Column(name = "LENGTH")
    private Double length;

    @Column(name = "WIDTH")
    private Double width;

    @Column(name = "HEIGHT")
    private Double height;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}